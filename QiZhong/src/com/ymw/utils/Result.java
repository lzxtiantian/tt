package com.ymw.utils;

import com.google.gson.Gson;

public class Result {
	public String code;
	public String msg;
	public Integer count;
	public Object data;
	public static String toClient(String code,String msg){
		Result r = new Result();
		r.code =code;
		r.msg = msg;
		r.count=0;
		Gson t = new Gson();
		return t.toJson(r);
	}
	public static String toClient(String code,String msg,Integer count,Object data){
		Result r = new Result();
		r.code =code;
		r.msg = msg;
		r.count=count;
		r.data = data;
		Gson t = new Gson();
		return t.toJson(r);
	}	
}
