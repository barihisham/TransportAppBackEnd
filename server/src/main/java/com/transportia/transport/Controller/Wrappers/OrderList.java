package com.transportia.transport.Controller.Wrappers;

import com.transportia.transport.Model.Order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderList {
    List<Order> orders = new ArrayList<>();
}
