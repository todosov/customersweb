package com.website.dao;

import com.website.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a.todosov.
 */

@Repository("customerDao")
public class CustomerDAOImpl extends AbstractDAO<Customer> implements CustomerDAO {
    @Override
    public List<Customer> findAllCustomers() {
        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }

    @Override
    public void saveCustomer(Customer customer) {
        getSession().save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username", username));
        return (Customer) criteria.uniqueResult();
    }
}
