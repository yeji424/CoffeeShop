package com.shop.cafe.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	Map<String, Object> storage = new HashMap(); // 저장소
			
	@GetMapping("getAllProducts")
	public List<Product> getAllProducts() {
		try {
			Object o = storage.get("firstPagePRoducts");
			
			// 없을 경우에만 db에 다녀옵니당
			if(o == null) {
				List<Product> list = productService.getAllProducts();
				storage.put("firstPageProducts", list);
				return list;
			}
			// 아니면 원래 저장된 것들 불러오기
			// 변경 데이터도 리턴 가능하겠지용
			return (List<Product>) o;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}