package com.ymw.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ymw.dao.ProductDao;
import com.ymw.entity.Product;
import com.ymw.utils.DBManager;

/**
 * 鍟嗗搧鎺ュ彛瀹炵幇绫�
 * 
 * @author
 *
 */
public class ProductDaoImpl implements ProductDao {

	/**
	 * 鍒嗛〉鏌ヨ鎵�鏈夌殑鍟嗗搧鍒楄〃
	 */
	@Override
	public List<Product> getAllProducts(Integer page, Integer limit) {
		Statement sta = null;
		ResultSet res = null;
		Connection conn = null;
		List<Product> list = new ArrayList<Product>();

		try {
			// 鍔犺浇椹卞姩寤虹珛杩炴帴 鍙戦�乻ql 鎵цsql 澶勭悊缁撴灉 鍏抽棴璧勬簮
			conn = DBManager.getConnection();
			sta = conn.createStatement();
			res = sta.executeQuery("select * from product limit " + (page - 1) * limit + "," + limit);
			while (res.next()) {
				Integer id = res.getInt("id");
				String name = res.getString("name");
				String url = res.getString("url");
				Double price = res.getDouble("price");
				String type = res.getString("type");

				list.add(new Product(id, name, url, price, type));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.closeAll(conn, sta, res);
		}

		return list;

	}

	/**
	 * 鏌ヨ鍟嗗搧鎬绘暟閲�
	 */
	@Override
	public Integer getCount() {
		Statement sta = null;
		ResultSet res = null;
		Connection conn = null;
		Integer num = 0;

		try {
			// 鍔犺浇椹卞姩寤虹珛杩炴帴 鍙戦�乻ql 鎵цsql 澶勭悊缁撴灉 鍏抽棴璧勬簮
			conn = DBManager.getConnection();
			sta = conn.createStatement();
			res = sta.executeQuery("select count(*) as num from product");
			if (res.next()) {
				num = res.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.closeAll(conn, sta, res);
		}

		return num;
	}

	@Override
	public List<Product> getProductByAny(Integer page, Integer limit, String name, double dPrice, double hPrice,
			String type) {
		Statement sta = null;
		ResultSet res = null;
		Connection conn = null;
		List<Product> list = new ArrayList<Product>();

		try {
			// 鍔犺浇椹卞姩寤虹珛杩炴帴 鍙戦�乻ql 鎵цsql 澶勭悊缁撴灉 鍏抽棴璧勬簮
			conn = DBManager.getConnection();
			sta = conn.createStatement();
			res = sta.executeQuery("select * from product where name like '%" + name + "%' and price>=" + dPrice
					+ " and price<=" + hPrice + " and type='" + type + "' limit " + (page - 1) * limit + "," + limit);

			/*
			 * String sql = "select * from product where name like '%" + name +
			 * "%' and price BETWEEN " + dPrice + " and " + hPrice + " and type='" + type +
			 * "'  LIMIT " + (page - 1) * limit + "," + limit + "";
			 */

			while (res.next()) {
				Integer id = res.getInt("id");
				String name1 = res.getString("name");
				String url = res.getString("url");
				Double price = res.getDouble("price");
				String type1 = res.getString("type");

				list.add(new Product(id, name1, url, price, type1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.closeAll(conn, sta, res);
		}

		return list;

	}

	@Override
	public Integer getCount(Integer page, Integer limit, String name, double dPrice, double hPrice, String type) {
		Statement sta = null;
		ResultSet res = null;
		Connection conn = null;
		Integer num = 0;

		try {
			// 鍔犺浇椹卞姩寤虹珛杩炴帴 鍙戦�乻ql 鎵цsql 澶勭悊缁撴灉 鍏抽棴璧勬簮
			conn = DBManager.getConnection();
			sta = conn.createStatement();
			res = sta.executeQuery("select count(*) as num from product where name like '%" + name + "%' and price>="
					+ dPrice + " and price<=" + hPrice + " and type='" + type + "' limit " + (page - 1) * limit + ","
					+ limit);

			if (res.next()) {
				num = res.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.closeAll(conn, sta, res);
		}

		return num;
	}

	/**
	 * 添加
	 */
	@Override
	public Integer addProduct(Product p) {
		String sql = "insert into product values(null,'" + p.getName() + "',null," + p.getPrice() + ",'" + p.getType()
				+ "')";

		/*
		 * String sql1 = "insert into product values(null,'" + p.getName() + "',null," +
		 * p.getPrice() + ",'" + p.getType() + "')";
		 */
		return DBManager.executeUpdate(sql);
	}

	/**
	 * 修改
	 */
	@Override
	public Integer updateProduct(Integer id, String name, double price, String type) {
		String sql = "update product set name='" + name + "',price=" + price + ",type='" + type + "' where id=" + id;
		return DBManager.executeUpdate(sql);
	}

	/**
	 * 删除
	 */
	@Override
	public Integer delProduct(Integer id) {
		String sql = "delete from product where id=" + id;
		return DBManager.executeUpdate(sql);
	}
}
