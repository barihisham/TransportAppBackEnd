package com.transportia.transport.Repository;

import com.transportia.transport.Model.Order.Order;
import com.transportia.transport.Model.Order.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order deleteOrderById(String id);

    List<Order> findAllByDriverUsername(String driverUsername);

    List<Order> findAllByOrderStatusContains(String orderStatus);

}
