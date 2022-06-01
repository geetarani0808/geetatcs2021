package com.demo.repositary;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Customer;
@Repository
public interface CustomerRepositary  extends MongoRepository<Customer, Long>{

}
