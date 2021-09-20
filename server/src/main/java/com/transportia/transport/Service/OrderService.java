package com.transportia.transport.Service;

import com.transportia.transport.Model.Order.Order;
import com.transportia.transport.Model.Order.OrderStatus;
import com.transportia.transport.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(String orderId) { return orderRepository.findById(orderId); }

    public Order assignDriverToOrder(String orderId, String driverUsername){
        Optional<Order> orderOptional = getOrder(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(OrderStatus.ASSIGNED);
            order.setAssignedDriverUsername(driverUsername);
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    public Order issuePickedUpOrder(String orderId){
        Optional<Order> orderOptional = getOrder(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(OrderStatus.PICKED_UP);
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    public Order issueDeliveredOrder(String orderId){
        Optional<Order> orderOptional = getOrder(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(OrderStatus.DELIVERED);
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    public Order deleteOrder(String orderId){
        return orderRepository.deleteOrderById(orderId);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getAllUnassignedOrders() { return orderRepository.findAllByOrderStatusContains("PLACED"); }

    public void assignOrderToDriver(Order order){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(order.getId()));
        Update update = new Update();
        update.set("driverUsername", order.getDriverUsername());
        mongoTemplate.updateFirst(query, update, Order.class);
    }

    public List<Order> getOrdersByDriverUsername(String driverUsername){
        return orderRepository.findAllByDriverUsername(driverUsername);
    }

    public Order getOrderById(String id){
        return orderRepository.findById(id).get();
    }

}
