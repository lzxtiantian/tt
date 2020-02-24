package com.ymw.dao;

import java.util.List;

import com.ymw.entity.Product;

/**
 * 鍟嗗搧鏁版嵁鎺ュ彛
 * @author 鏉ㄦⅵ浼�
 *
 */
public interface ProductDao {
	/**
	 * 鍒嗛〉鏌ヨ鎵�鏈夊晢鍝佸垪琛�
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Product> getAllProducts(Integer page,Integer limit);
	
	/**
	 * 鏌ヨ鍟嗗搧鐨勬�绘暟閲�
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * 多条件分页模糊查询
	 * @param page
	 * @param limit
	 * @param name
	 * @param dPrice
	 * @param hPrice
	 * @param type
	 * @return
	 */
	public List<Product> getProductByAny(Integer page,Integer limit,String name,double dPrice,double hPrice,String type );
	/**
	 * 获取模糊查询的条数
	 * @param page
	 * @param limit
	 * @param name
	 * @param dPrice
	 * @param hPrice
	 * @param type
	 * @return
	 */
	public Integer getCount(Integer page,Integer limit,String name,double dPrice,double hPrice,String type);
	
	/**
	 * 添加
	 * @param p
	 * @return
	 */
	public Integer addProduct(Product p);
	/**
	 * 修改
	 * @param id
	 * @param name
	 * @param price
	 * @param type
	 * @return
	 */
	public Integer updateProduct(Integer id,String name,double price,String type);
		
	
	public Integer delProduct(Integer id) ;






}
