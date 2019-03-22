package aw.select.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aw.select.service.RequestService;



@RestController  
@RequestMapping
public class GetPriceController {
	
	@Autowired
	private RequestService requestService;
	
	
	
	
	/*
	 * 查询价格信息路由
	 * 获取url参数
	 * 有值的barcode：26070520
	 * 测试地址：
	 * 正常商品：localhost:8080/scan/5029053021454/50
	 *        localhost:8080/scan/22008817/50
	 * 折扣商品：localhost:8080/scan/042283641921/50
	 * 不定重肉类商品(正常)：localhost:8080/scan/2811500002009/50
	 * 不定重肉类商品(折扣)：localhost:8080/scan/2811600005009/50
	 * 
	 * 
	 */
	 @GetMapping("/scan/{barcode}/{sktype}")
	 public String selectPrice(@PathVariable("barcode") String barcode,@PathVariable("sktype") String sktype){	
		 return requestService.selectPrice(requestService.setData(barcode), sktype);
	 }
	 
	


}
