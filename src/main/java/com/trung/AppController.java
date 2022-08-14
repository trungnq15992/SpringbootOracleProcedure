package com.trung;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	@Autowired
	private ProductDAOImplement service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) throws SQLException {
		List<Product> listProducts = service.getListProduct();
		model.addAttribute("listProducts",listProducts);
		return "index";
	}
}
