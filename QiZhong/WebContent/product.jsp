<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" type="text/css"
	href="layui(layui-v2.4.3)/css/layui.css" />
<script src="layui(layui-v2.4.3)/layui.js"></script>
</head>

<body>
	<script>
		var addIndex;
		layui.use([ 'layer', 'form', 'table' ], function() {
			var layer = layui.layer;
			var table = layui.table;
			var form = layui.form;
			var $ = layui.$;

			//渲染表格
			table.render({
				elem : '#demo',//指定哪个容器
				url : 'product?method=getAllProducts',
				page : true,//是否开启分页
				method : 'post',//提交方式
				cols : [ [ {
					field : 'url',
					title : '商品图片',
					width : 200,
					templet : '<div><img src="img/{{d.url}}"/></div>'
				}, {
					field : 'id',
					title : '商品标号',
					width : 200
				}, {
					field : 'name',
					title : '商品名称',
					width : 200
				}, {
					field : 'price',
					title : '商品价格',
					width : 200
				}, {
					field : 'type',
					title : '商品类型',
					width : 200
				}, {

					title : '操作',
					width : 400,
					toolbar : '#barDemo'
				} ] ],
				id : 'testTable'
			});
			active = {
				aaa : function() {
					table.reload('testTable', {
						url : 'product?method=getProByAny',
						where : {
							'name' : $('#name').val(),
							'dPrice' : $('#dPrice').val(),
							'hPrice' : $('#hPrice').val(),
							'type' : $('#type').val(),
						},
						page : {
							curr : 1
						}
					});
				}
			};
			$('#search').click(function() {
				active.aaa();
			});

			$('#add').click(function() {
				addIndex = layer.open({
					type : 1,
					content : $('#addForm'),
					area : [ '500px', '400px' ]
				});
			});

			//监听添加按钮
			form.on('submit(aformDemo)', function() {

				$.post('product?method=addPro',//地址		
				$('#addForm').serialize(), function(res) {

					layer.msg('添加成功', {
						icon : 4
					}, function() {
						layer.close(addIndex);
						window.location.reload();
					});

				}, 'json');

			});

			//监听工具条 
			table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值" obj某一行
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

				if (layEvent === 'detail') { //查看
					//do somehing
					$('#sname').val(data.name);
					$('#sprice').val(data.price);
					$('#stype').val(data.type);
					$('#sid').val(data.id);
					layer.open({
						type : 1,
						content : $('#sForm'),
						title : '显示' + data.name + '的产品信息',
						area : [ '500px', '400px' ]
					});
				} else if (layEvent === 'del') { //删除
					layer.confirm('真的删除行么', function(index) {
						obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
						layer.close(index);
						//向服务端发送删除指令
						$.post('product?method=delPro',//地址		
						{
							'id' : data.id
						}, function(res) {

							layer.msg('删除成功', {
								icon : 4
							});

						}, 'json');

					});

				} else if (layEvent === 'edit') { //编辑
					//do something
					$('#updatename').val(data.name);
					$('#updateprice').val(data.price);
					$('#updatetype').val(data.type);
					$('#updateid').val(data.id);
					layer.open({
						type : 1,
						content : $('#updateForm'),
						title : '修改产品信息',
						area : [ '500px', '400px' ]
					});
					//同步更新缓存对应的值
					obj.update({
						username : '123',
						title : 'xxx'
					});
				} else if (layEvent === 'LAYTABLE_TIPS') {
					layer.alert('Hi，头部工具栏扩展的右侧图标。');
				}
			});

			//监听修改按钮
			form.on('submit(updateformDemo)', function() {

				$.post('product?method=updatePro',//地址		
				$('#updateForm').serialize(), function(res) {

					layer.msg('修改成功', {
						icon : 4
					}, function() {
						layer.close(addIndex);
						window.location.reload();
					});

				}, 'json');

			});
		});
	</script>


	<form class="layui-form" onsubmit="return false;">

		商品名
		<div class="layui-input-inline">
			<input type="text" name="name" id="name" required
				lay-verify="required" placeholder="请输入商品名" autocomplete="off"
				class="layui-input">
		</div>

		价格
		<div class="layui-input-inline">
			<input type="text" name="dPrice" id="dPrice" required
				lay-verify="required" placeholder="请输入最低价" autocomplete="off"
				class="layui-input">
		</div>
		---
		<div class="layui-input-inline">
			<input type="text" name="hPrice" id="hPrice" required
				lay-verify="required" placeholder="请输入最高价" autocomplete="off"
				class="layui-input">
		</div>
		类型
		<div class="layui-input-inline">

			<select name="type" id="type" lay-filter="aihao">

				<option value="上架">上架</option>
				<option value="下架">下架</option>

			</select>


		</div>

		<button class="layui-btn" lay-filter="formDemo" id="search"
			data-type="aaa">搜索</button>
		<button class="layui-btn" lay-filter="addformDemo" id="add"
			data-type="bbb">添加</button>
	</form>
	<table id="demo" lay-filter="test"></table>
	<!-- 行内工具条 -->
	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  
 
</script>


	<!-- 添加页面开始 -->
	<form class="layui-form" action="" style="display: none;" id="addForm"
		onsubmit="return false;">
		<div class="layui-form-item">
			<label class="layui-form-label">产品</label>
			<div class="layui-input-inline" style="width: 180px">
				<input type="text" name="aname" id="aname" required
					lay-verify="required" placeholder="请输入产品名" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">价格</label>
			<div class="layui-input-inline">
				<input type="text" name="aprice" id="aprice" required
					lay-verify="required" placeholder="请输入价格" autocomplete="off"
					class="layui-input">
			</div>

		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">类型</label>
			<div class="layui-input-inline">
				<select name="atype" id="atype" style="width: 50px"
					lay-verify="required">
					<option value=""></option>
					<option value="上架">上架</option>
					<option value="下架">下架</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="aformDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<!--添加页面结束  -->


	<!-- 修改页面开始 -->
	<form class="layui-form" action="" style="display: none;"
		id="updateForm" onsubmit="return false;">
		<div class="layui-form-item" style="display: none;">
			<label class="layui-form-label">编号</label>
			<div class="layui-input-inline" style="width: 180px">
				<input type="text" name="updateid" id="updateid" required
					lay-verify="required" placeholder="请输入产品名" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">产品</label>
			<div class="layui-input-inline" style="width: 180px">
				<input type="text" name="updatename" id="updatename" required
					lay-verify="required" placeholder="请输入产品名" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">价格</label>
			<div class="layui-input-inline">
				<input type="text" name="updateprice" id="updateprice" required
					lay-verify="required" placeholder="请输入价格" autocomplete="off"
					class="layui-input">
			</div>

		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">类型</label>
			<div class="layui-input-inline">
				<select name="updatetype" id="updatetype" style="width: 50px"
					lay-verify="required">
					<option value=""></option>
					<option value="上架">上架</option>
					<option value="下架">下架</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="updateformDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<!--修改页面结束  -->


	<!-- 查看页面开始 -->
	<form class="layui-form" action="" style="display: none;" id="sForm"
		onsubmit="return false;">
		<div class="layui-form-item">
			<label class="layui-form-label">编号</label>
			<div class="layui-input-inline" style="width: 180px">
				<input type="text" name="sid" id="sid" required
					lay-verify="required" placeholder="请输入产品名" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">产品</label>
			<div class="layui-input-inline" style="width: 180px">
				<input type="text" name="sname" id="sname" required
					lay-verify="required" placeholder="请输入产品名" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">价格</label>
			<div class="layui-input-inline">
				<input type="text" name="sprice" id="sprice" required
					lay-verify="required" placeholder="请输入价格" autocomplete="off"
					class="layui-input">
			</div>

		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">类型</label>
			<div class="layui-input-inline">
				<select name="stype" id="stype" style="width: 50px"
					lay-verify="required">
					<option value=""></option>
					<option value="上架">上架</option>
					<option value="下架">下架</option>
				</select>
			</div>
		</div>


	</form>
	<!--查看页面结束  -->
</body>

</html>