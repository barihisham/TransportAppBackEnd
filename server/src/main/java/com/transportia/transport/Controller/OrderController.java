package com.transportia.transport.Controller;

import com.transportia.transport.Controller.Wrappers.OrderList;
import com.transportia.transport.Model.Order.Order;
import com.transportia.transport.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/getOrderById/{orderId}")
    public Order getOrderById(@PathVariable String orderId) {
        Optional<Order> order = orderService.getOrder(orderId);
        return order.orElse(null);
    }

    @GetMapping(path = "/getAllUnassignedOrders")
    public List<Order> getAllUnassignedOrders() { return orderService.getAllUnassignedOrders(); }

    @PostMapping
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @PostMapping("/assign")
    public Order assignDriverToOrder(@RequestBody Map<String, String> json){
        return orderService.assignDriverToOrder(json.get("orderId"), json.get("driverUsername"));
    }

    @PostMapping("/pickup/{orderId}")
    public Order issuePickedUpOrder(@PathVariable String orderId){
        return orderService.issuePickedUpOrder(orderId);
    }

    @PostMapping("/deliver/{orderId}")
    public Order issueDeliveredOrder(@PathVariable String orderId){
        return orderService.issueDeliveredOrder(orderId);
    }

    @DeleteMapping(path = "{orderId}")
    public Order deleteOrder(@PathVariable("orderId") String orderId){
        return orderService.deleteOrder(orderId);
    }

    @GetMapping(path = "{driverUsername}")
    public OrderList getOrdersByDriverUsername(@PathVariable("driverUsername") String driverUsername){
        return new OrderList(orderService.getOrdersByDriverUsername(driverUsername));
    }

    @PutMapping
    public void assignOrderToDriver(@RequestBody Order order){
        orderService.assignOrderToDriver(order);
    }

}
