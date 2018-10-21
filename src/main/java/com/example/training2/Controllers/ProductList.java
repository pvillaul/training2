package com.example.training2.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.training2.Models.Product;
import com.example.training2.Models.Error;

@Controller
public class ProductList {
	private Product prod = new Product("12345","Coca-Cola","Bebida",5);
	private Map<String, Product> productos = new HashMap<String, Product>() {{ put ("12345",prod); }};
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("productos",productos);
		return "list";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("product",productos.get(id));
		return "resume";
	}
	
	@GetMapping("/remove/{id}")
	public String delete(@PathVariable String id, Model model) {
		if(productos.containsKey(id)) {
			productos.remove(id);
			model.addAttribute("productos",productos);
			return "redirect:/list";
		} else {
			Error error = new Error("Error al Eliminar","No se ha podido eliminar el producto selecionado","list","Atras");
			model.addAttribute("error",error);
			return "aviso";
		}
	}
	
	@GetMapping("/addForm")
	public String addProd(@ModelAttribute Product product) {
		return "addForm";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String newProd(@ModelAttribute("product") Product product,BindingResult result, ModelMap model) {
		if(!productos.containsKey(product.getCode())) {
			model.addAttribute("code", product.getCode());
	        model.addAttribute("name", product.getName());
	        model.addAttribute("description", product.getDescription());
	        model.addAttribute("price", product.getPrice());
	        productos.put(product.getCode(), product);
	        return "resume";
		} else {
			Error error = new Error("Error al Crear el Producto","El codigo introducido coincide con un producto existente","addForm","Volver al formulario");
			model.addAttribute("error",error);
			return "aviso";
		}
	}
}