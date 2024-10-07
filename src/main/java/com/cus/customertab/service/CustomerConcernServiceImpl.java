package com.cus.customertab.service;

import com.cus.customertab.constants.CustomerConstants;
import com.cus.customertab.dto.CustomerConcernDTO;
import com.cus.customertab.entity.CustomerConcern;
import com.cus.customertab.mapper.CustomerConcernMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerConcernServiceImpl implements CustomerConcernService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CustomerConcernMapper customerConcernMapper;

    // To get all customer concerns
    @Override
    @Transactional(readOnly = true)
    public List<CustomerConcernDTO> getAllConcerns() {
        Session session = sessionFactory.getCurrentSession();
        List<CustomerConcern> concerns = session.createQuery("FROM CustomerConcern", CustomerConcern.class)
                .getResultList();
        return concerns.stream()
                .map(customerConcernMapper::customerConcernToDTO)
                .toList();
    }

    // To get a customer concern by ID
    @Override
    @Transactional(readOnly = true)
    public CustomerConcernDTO getConcernById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CustomerConcern concern = session.get(CustomerConcern.class, id);
        return customerConcernMapper.customerConcernToDTO(concern);
    }

    // To add a new customer concern
    @Override
    @Transactional
    public String addNewConcern(CustomerConcernDTO customerConcernDTO) {
        Session session = sessionFactory.getCurrentSession();
        CustomerConcern concern = customerConcernMapper.dtoToCustomerConcern(customerConcernDTO);
        session.persist(concern);
        return CustomerConstants.CUSTOMER_CONCERN_ADDED;
    }

    // To modify a customer concern
    @Override
    @Transactional
    public String modifyConcern(CustomerConcernDTO customerConcernDTO) {
        Session session = sessionFactory.getCurrentSession();
        CustomerConcern existingConcern = session.get(CustomerConcern.class, customerConcernDTO.getId());
        CustomerConcern updatedConcern = customerConcernMapper.dtoToCustomerConcern(customerConcernDTO);
        updatedConcern.setId(existingConcern.getId());
        session.merge(updatedConcern);
        return CustomerConstants.CUSTOMER_CONCERN_UPDATED;
    }

    // To delete a customer concern
    @Override
    @Transactional
    public String deleteConcern(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CustomerConcern existingConcern = session.get(CustomerConcern.class, id);
        session.remove(existingConcern);
        return CustomerConstants.CUSTOMER_CONCERN_DELETED;
    }
}
