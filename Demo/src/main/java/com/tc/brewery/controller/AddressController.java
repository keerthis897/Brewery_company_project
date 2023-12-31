package com.tc.brewery.controller;

import com.tc.brewery.entity.Address;
import com.tc.brewery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/address/{user_id}")
    public List<Map<String, Object>> getAddressesByUserId(@PathVariable("user_id") Long userId) {
        return addressService.getAddressesByUserId(userId);
    }
}
