package com.tc.brewery.controller;

import com.tc.brewery.entity.Address;
import com.tc.brewery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add_address/{userId}")
    public ResponseEntity<Void> createAddressForUser(
            @PathVariable Long userId,
            @RequestBody Address address
    ) {
        return addressService.createAddressForUser(userId, address);
    }
}
