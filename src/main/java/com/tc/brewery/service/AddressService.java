package com.tc.brewery.service;

import com.tc.brewery.entity.Address;
import com.tc.brewery.entity.User;
import com.tc.brewery.repository.AddressRepository;
import com.tc.brewery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<Void> createAddressForUser(Long userId, Address address) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        address.setUser(user);
        addressRepository.save(address);
        return ResponseEntity.ok().build();
    }
}
