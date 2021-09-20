package com.transportia.transport.Model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Package {

    private String packageNumber; //TODO: Kommer genereras själv i guess, lastnummer? MUST BE UNIQUE
    private int quantity;
    private PackageType packageType;
}
