package com.itc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itc.beans.Product;
import com.itc.dao.ProductDAO;



@Controller
public class CartController4 {
	@Autowired
	ProductDAO ProductDAO;
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	   public String redirect3() {
	      return "redirect:/pages/login.html";
	   }*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	   public String redirect2() {
	      return "redirect:/pages/homepage.html";
	   }
	   @RequestMapping(value = "/page2", method = RequestMethod.GET)
	   public String redirect1() {
	      return "redirect:/pages/page2.html";
	   }
	
	@RequestMapping(value = "/product1", method = RequestMethod.GET)
	public String product1() {
		return "product1";
	}

	@RequestMapping(value = "/checkAvailability", method = RequestMethod.GET)
	public String result(HttpServletRequest request, Model model) {
		System.out.println("inside controller check availability method");
		int id=0,status=0,quantity=0;
		boolean stat= false;
		String strQuan=null,pname;
		id=Integer.parseInt(request.getParameter("id"));

		//		Product p = ProductDAO.get(Integer.parseInt(request.getParameter("id")));
		strQuan= request.getParameter("quantity");
		/*int quan=0;*/
		if(strQuan==null || strQuan.trim().equals("")) {
			status=-1;
		}else {
			pname =request.getParameter("pname");
			double price=Double.parseDouble(request.getParameter("price"));
			quantity=Integer.parseInt(request.getParameter("quantity"));

			Product p= new Product(id, pname, price, quantity);
			stat= ProductDAO.checkWS(p);
			if(stat==false){
				status=0;
			}else{
				status=1;
			}

		}
		System.out.println(strQuan);
		System.out.println(status);
		System.out.println(" out controller result method");
		model.addAttribute("status", status);

		return "checkAvailability";
	}


	@RequestMapping(value="/viewCart")
	public ModelAndView listProduct(ModelAndView model) throws IOException{
		List<com.itc.beans.Product> listProduct = ProductDAO.list();
		model.addObject("listProduct", listProduct);
		model.setViewName("viewCart");
		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addToCart(@ModelAttribute com.itc.beans.Product product) {
		boolean status = false;

		status=ProductDAO.add(product);

		String s= null;
		if(status==true){
			s="Product successfully added to your cart";
		}else{
			s="  "+product.getPname()+" already exists in your cart..!";
		}
		return new ModelAndView("insert","status",s);
	}
	
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public ModelAndView delete(@PathVariable int id, Model model) {
			//int id = Integer.parseInt(request.getParameter("id"));
		 System.out.println("inside delete..!!"+ id);
		   boolean status = ProductDAO.delete(id);
		   String s=null;
			if(status==true){
				s="Successfully deleted Product..!!";
			}else{
				s="Error occured while processing your request please try again later";
			}
			return new ModelAndView("delete","status",s);
		}
	   
	   @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public ModelAndView edit(@PathVariable int id, Model model) {
		   
			//int id = Integer.parseInt(request.getParameter("id"));
		   com.itc.beans.Product product = ProductDAO.get(id);
			ModelAndView model1 = new ModelAndView("edit","command",product);			
			return model1;
		}
	   
	   @RequestMapping(value = "/update", method = RequestMethod.POST)
		public ModelAndView updateToCart(@ModelAttribute com.itc.beans.Product product) {
			boolean status = ProductDAO.update(product);	
			String s=null;
			if(status==true){
				s="Product quantity successfully updated in your cart";
			}else{
				s= "Error occured while processing your request please try again later";
			}
			ModelAndView model = new ModelAndView("update");
			model.addObject("status", s);
			return model;
		}
	   
	  /* @RequestMapping(value = "/product1", method = RequestMethod.GET)
	    public ModelAndView product1() {
			return new ModelAndView("product1", "command", new Product());   
	   }*/ @RequestMapping(value = "/product2", method = RequestMethod.GET)
	    public ModelAndView product2() {
			return new ModelAndView("product2", "command", new com.itc.dao.ProductDAO());   
	   } 
	   @RequestMapping(value = "/product3", method = RequestMethod.GET)
	    public ModelAndView product3() {
			return new ModelAndView("product3", "command", new com.itc.dao.ProductDAO());   
	   } 
	   @RequestMapping(value = "/product4", method = RequestMethod.GET)
	    public ModelAndView product4() {
			return new ModelAndView("product4", "command", new com.itc.dao.ProductDAO());   
	   } 
	   @RequestMapping(value = "/product5", method = RequestMethod.GET)
	    public ModelAndView product5() {
			return new ModelAndView("product5", "command", new com.itc.dao.ProductDAO());   
	   } 
	   @RequestMapping(value = "/product6", method = RequestMethod.GET)
	    public ModelAndView product6() {
			return new ModelAndView("product6", "command", new com.itc.dao.ProductDAO());  
	   }
	   @RequestMapping(value = "/connection", method = RequestMethod.GET)
	   public ModelAndView Connect(HttpServletRequest request)
	   {
		  ModelAndView model = new ModelAndView("Connection");
		  String uname = request.getParameter("unmae");
		  String pwd = request.getParameter("pwd");
		  model.addObject("uname", uname);
		  model.addObject("pwd", pwd);
		   return model;
	   }
}
