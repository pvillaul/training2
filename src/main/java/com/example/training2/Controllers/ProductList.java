package com.example.training2.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.training2.Models.Product;

@Controller
public class ProductList {
	//private Map<String, Product> productos = new HashMap<String, Product>();
	
	@GetMapping("/list")
	public String list(Model model) {
		Product prod = new Product("123-ABC","Lays","Patatas saladas",15);
		model.addAttribute("name","World");
		model.addAttribute("obj",prod);
		return "list";
	}
	
	@GetMapping("/list")
	public String sendProd(Model model) {
		model.addAttribute("product", "pppp");
		return "add";
	}
	
	@PostMapping("/list")
	public String readProd(@ModelAttribute Product prod) {
		return "resume";
	}
	
	@GetMapping("/add")
	public String addProd(Model model) {
		Product prod = new Product("123-ABC","Lays","Patatas saladas",15);
		model.addAttribute("prod", prod);
		return "add";
	}
	
	@PostMapping("/list/{id}")
	public String showProd(@ModelAttribute Product prod) {
		return "showProd";
	}
	
	@GetMapping("/list/{id}")
	public String path(@PathVariable String id) {
		return id;
	}
}