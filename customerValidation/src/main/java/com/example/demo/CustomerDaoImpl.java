package com.example.demo;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class CustomerDaoImpl implements CustomerDao{
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
 
    public List<Customer> getAllCustomers() {
        Session session = this.sessionFactory.openSession();
        List<Customer>  customerList = session.createQuery("from Customer").list();
        return customerList;
    }
 
    public Customer getCustomer(int id) {
        Session session = this.sessionFactory.openSession();
        Customer customer = (Customer) session.get(Customer.class, id);
        return customer;
    }
 
    public Customer addCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        session.save(customer);
        return customer;
    }

    public void updateCustomer(Customer customer) {
    	
        Session session = this.sessionFactory.openSession();
        org.hibernate.Transaction txn = session.beginTransaction();
        
        //session.createQuery("update Customer set email='jjjjjjjjj' where id=1").executeUpdate();
        
       
        session.update(customer);
        txn.commit();
    }
 
    public void deleteCustomer(int id) {
        Session session = this.sessionFactory.openSession();
        org.hibernate.Transaction txn = session.beginTransaction();
        Customer p = (Customer) session.load(Customer.class, new Integer(id));
        if (null != p) {
            session.delete(p);
            txn.commit();
        }
    } 
}
 