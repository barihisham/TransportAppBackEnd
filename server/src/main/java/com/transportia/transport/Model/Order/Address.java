package com.transportia.transport.Model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {

    private String street;
    private String streetNumber;
    private String zip;
    private String city;
    private String region;

}
