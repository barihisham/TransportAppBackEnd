package com.transportia.transport.Model.Order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderInfo {

    private String name;
    private Address address;
    private List<String> phoneNumbers;
    private LocalDateTime earliestTime;
    private LocalDateTime latestTime;
    private String doorCode;

}
