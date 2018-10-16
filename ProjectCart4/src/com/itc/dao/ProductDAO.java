package com.itc.dao;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.util.HibernateUtil;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class ProductDAO{

	
	public boolean add(com.itc.beans.Product product) {
		  Session session = HibernateUtil.openSession();
	      Transaction tx = null;
	      boolean status= false;
	      
	      try {
	         tx = session.beginTransaction();
	        session.save(product); 
	         //employeeID = (Integer) session.save(employee); 
	         tx.commit();
	         status=true;

//	      } catch (HibernateException e) {
	      } catch (javax.persistence.PersistenceException e) {
	         if (tx!=null) tx.rollback();
	         if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException)

	         {status=false;}

	         status= false;
	         
	      }
	      finally {
	         session.close(); 
	      }
	      return status;
	}


	public boolean update(com.itc.beans.Product product) {
		  Session session = HibernateUtil.openSession();
	      Transaction tx = null;
	      boolean status= false;
	      try {
	         tx = session.beginTransaction();
	         com.itc.beans.Product product1 = (com.itc.beans.Product)session.get(com.itc.beans.Product.class, product.getId());
	   
	         product1.setQuantity(product.getQuantity());
	         session.update(product1); 
	         status=true;
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         status=false;
	      } finally {
	         session.close(); 
	      }
		return status;
	}


	public boolean delete(int id) {
		Session session = HibernateUtil.openSession();
	      Transaction tx = null;
	      boolean status= false;
	      try {
	         tx = session.beginTransaction();
	         com.itc.beans.Product product = (com.itc.beans.Product)session.get(com.itc.beans.Product.class, id); 
	         session.delete(product); 
	         tx.commit();
	         status=true;
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         status=false;
	      } finally {
	         session.close(); 
	      }
	      return status;
	}


	public com.itc.beans.Product get(int id) {
		com.itc.beans.Product prod=null;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try {
			tx = session.getTransaction();
			tx.begin();
			/*Query query = session.createQuery("from product where id='"+id+"'");

			prod = (Product)query.uniqueResult();*/
			prod = (com.itc.beans.Product)session.get(com.itc.beans.Product.class, id);
			System.out.println(prod);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}


		return prod;
	}


	public List<com.itc.beans.Product> list() {
		List<com.itc.beans.Product> list=new ArrayList<com.itc.beans.Product>();
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = session.createQuery("from Product").list();                        
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}


	public boolean checkWS(com.itc.beans.Product p) {
		boolean status=false;
		String webServiceURI = "http://BLRPRGFWD31247";
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI serviceURI = UriBuilder.fromUri(webServiceURI).port(8080).path("RestfulWS1").build();
		WebTarget webTarget = client.target(serviceURI);
		String st=webTarget.path("rest").path("CheckQuantity").path("in").queryParam("id", p.getId()).queryParam("quantity", p.getQuantity()).request().accept(MediaType.TEXT_PLAIN).get(String.class);
System.out.println(st);
		if(st.equals("available")){
			status=true;
		}else{
			status=false;
		}
			return status;
		}
		
		
		
	}
	
	

