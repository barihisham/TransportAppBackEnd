package com.transportia.transport.Model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public Order(OrderInfo pickUpOrder, OrderInfo deliveryOrder, String customerMessage, String internalInfo, List<Package> packageList) {
        this.pickUpOrder = pickUpOrder;
        this.deliveryOrder = deliveryOrder;
        this.customerMessage = customerMessage;
        this.internalInfo = internalInfo;
        this.packageList = packageList;
    }

    @Id
    private String id;
    private OrderStatus orderStatus;
    private String assignedDriverUsername;
    private String customerUsername;
    private LocalDateTime orderPlaced;
    private OrderInfo pickUpOrder;
    private OrderInfo deliveryOrder;

    private String customerMessage;
    private String internalInfo;
    private String driverUsername;
    private List<Package> packageList;



}
