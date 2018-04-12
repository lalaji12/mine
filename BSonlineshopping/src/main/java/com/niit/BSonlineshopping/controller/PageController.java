package com.niit.BSonlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.BSonlineshoppingBackEnd.dao.CategoryDAO;
import com.niit.BSonlineshoppingBackEnd.dao.ProductDAO;
import com.niit.BSonlineshoppingBackEnd.dto.Category;
import com.niit.BSonlineshoppingBackEnd.dto.Product;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "home", "/index" })
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		// passing the list of categories

		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);

		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);

		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);

		return mv;
	}

	/**
	 * Methods to load all the products and based on category
	 * 
	 * 
	 */

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing the list of categories

		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);

		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showAllProducts(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch a single category

		Category category = null;

		category = categoryDAO.get(id);

		mv.addObject("title",category.getName());

		// passing the list of categories

		mv.addObject("categories", categoryDAO.list());
		
		//passing the single category object
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts", true);

		return mv;
	}


/*
*Viewing a single product
*/

@RequestMapping(value= "/show/{id}/product")
public ModelAndView showSingleProduct(@PathVariable int id){
	
	ModelAndView mv=new ModelAndView("page");

	Product product=productDAO.get(id);
	
	
	//update the view count

	product.setViews(product.getViews()+1);
	productDAO.update(product);
	
	//--------------------------
	
	mv.addObject("title",product.getName());
	mv.addObject("product",product);
	
	mv.addObject("userClickShowProduct",true);
	
	return mv;
}
}