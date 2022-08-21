package com.trung;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) throws SQLException {
	    service.deleteById(id);
	    return "redirect:/";       
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new_product";
	}
	
	@RequestMapping("/save")
	public String addProduct(@ModelAttribute("product") Product product) throws SQLException {
	    service.addProduct(product);
	    return "redirect:/";       
	}
}
