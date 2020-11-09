package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.*;
import com.example.utl.*;
import com.example.web.*;
/**
 * CRUD database operations
 * @author Ramesh Fadatare
 *
 */
public class ProductDao {
	
	/**
	 * Save User
	 * @param user
	 */
	public void saveProduct(Products product) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(product);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	    public Long doInHibernate(Session session) {
	        return (Long) session.createQuery("select count(newProduct.barcode) from Products someEntity ...").uniqueResult();
	    }
	/**
	 * Update product
	 * @param product
	 */
	public void updateProduct(Products product) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(product);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Delete product
	 * @param barcode
	 */
	public void deleteProduct(String barcode) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a product object
			Products product = session.get(Products.class, barcode);
			// commit transaction
			transaction.commit();
		} catch (Exception e){
			System.out.println("Exception in get");
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Get product By ID
	 * @param barcode
	 * @return
	 */
	public Products getProduct(String barcode) {

		Transaction transaction = null;
		Products product = null;
		try (Session session = (HibernateUtil.getSessionFactory()).openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an product object
			product = session.get(Products.class, barcode);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return product;
	}
	
	/**
	 * Get all products
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Products> getAllProducts() {

		Transaction transaction = null;
		List<Products> listOfProduct = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an product object
			
			listOfProduct = session.createQuery("from Products").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfProduct;
	}
}
