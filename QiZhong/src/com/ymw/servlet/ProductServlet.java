package com.ymw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ymw.dao.ProductDao;
import com.ymw.dao.impl.ProductDaoImpl;
import com.ymw.entity.Product;
import com.ymw.utils.Result;

/**
 * 鍟嗗搧servlet
 * 
 * @author 鏉ㄦⅵ浼�
 *
 */
@WebServlet("/product")
public class ProductServlet extends BaseServlet {

	/**
	 * 鑾峰彇鍟嗗搧鍒楄〃
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao pd = new ProductDaoImpl();
		String page = req.getParameter("page") == null ? "1" : req.getParameter("page");
		String limit = req.getParameter("limit") == null ? "10" : req.getParameter("limit");
		Integer count = pd.getCount();

		List<Product> list = pd.getAllProducts(Integer.parseInt(page), Integer.parseInt(limit));
		PrintWriter out = resp.getWriter();
		if (list != null) {
			out.write(Result.toClient("0", "鑾峰彇鎴愬姛", count, list));
		} else {
			out.write(Result.toClient("1", "鑾峰彇澶辫触"));
		}

		out.flush();
		out.close();
	}
	/**
	 * 多条件模糊分页查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getProByAny(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao pd = new ProductDaoImpl();
		String page = req.getParameter("page") == null ? "1" : req.getParameter("page");
		String limit = req.getParameter("limit") == null ? "10" : req.getParameter("limit");
		String name=req.getParameter("name");
		Double dPrice = Double.valueOf(req.getParameter("dPrice"));
		Double hPrice = Double.valueOf(req.getParameter("hPrice"));
		String type = req.getParameter("type");
		
		Integer count = pd.getCount(Integer.parseInt(page), Integer.parseInt(limit), name, dPrice, hPrice, type);

		List<Product> list = pd.getProductByAny(Integer.parseInt(page), Integer.parseInt(limit), name, dPrice, hPrice, type);
		PrintWriter out = resp.getWriter();
		if (list != null) {
			out.write(Result.toClient("0", "鑾峰彇鎴愬姛", count, list));
		} else {
			out.write(Result.toClient("1", "鑾峰彇澶辫触"));
		}

		out.flush();
		out.close();
	}
	
	/**
	 * 添加
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addPro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao pd = new ProductDaoImpl();
		String name=req.getParameter("aname");
		Double price = Double.valueOf(req.getParameter("aprice"));
		String type = req.getParameter("atype");
		Integer i = pd.addProduct(new Product(null, name, null, price, type));

		PrintWriter out = resp.getWriter();
		if (i>0) {
			out.write(Result.toClient("0", "鑾峰彇鎴愬姛"));
		} else {
			out.write(Result.toClient("1", "鑾峰彇澶辫触"));
		}

		out.flush();
		out.close();
	}
	

	/**
	 * 修改
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatePro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao pd = new ProductDaoImpl();
		String name=req.getParameter("updatename");
		Double price = Double.valueOf(req.getParameter("updateprice"));
		String type = req.getParameter("updatetype");
		Integer id=Integer.parseInt(req.getParameter("updateid"));
		
		Integer i = pd.updateProduct(id, name, price, type);

		PrintWriter out = resp.getWriter();
		if (i>0) {
			out.write(Result.toClient("0", "鑾峰彇鎴愬姛"));
		} else {
			out.write(Result.toClient("1", "鑾峰彇澶辫触"));
		}

		out.flush();
		out.close();
	}
	
	/**
	 * 
	 * 
	 * 删除
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delPro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao pd = new ProductDaoImpl();
		Integer id=Integer.parseInt(req.getParameter("id"));
		
		Integer i = pd.delProduct(id);

		PrintWriter out = resp.getWriter();
		if (i>0) {
			out.write(Result.toClient("0", "鑾峰彇鎴愬姛"));
		} else {
			out.write(Result.toClient("1", "鑾峰彇澶辫触"));
		}

		out.flush();
		out.close();
	}
}
