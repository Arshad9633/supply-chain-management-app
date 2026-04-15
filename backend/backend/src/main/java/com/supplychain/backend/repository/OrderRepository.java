package com.supplychain.backend.repository;

import com.supplychain.backend.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByProductId(String productId);

    List<Order> findByWarehouseId(Integer warehouseId);

    List<Order> findByOrderStatus(String orderStatus);
}