package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Order;

@Repository
public interface MyRepo extends JpaRepository<Order, Long> {

}
