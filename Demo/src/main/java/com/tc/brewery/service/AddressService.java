package com.tc.brewery.service;

import com.tc.brewery.entity.Address;
import com.tc.brewery.entity.User;
import com.tc.brewery.repository.AddressRepository;
import com.tc.brewery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<Void> createAddressForUser(Long userId, Address newAddress) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Check if the address already exists for the user
        Address existingAddress = addressRepository.findByUserAndAddressAndLatAndLng(
                user, newAddress.getAddress(), newAddress.getLat(), newAddress.getLng());

        if (existingAddress != null) {
            return ResponseEntity.badRequest().build();
        }

        newAddress.setUser(user);
        addressRepository.save(newAddress);
        return ResponseEntity.ok().build();
    }

    public List<Map<String, Object>> getAddressesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Map<String, Object>> addressDetailsList = new ArrayList<>();
            for (Address address : user.getAddressList()) {
                Map<String, Object> addressDetails = new HashMap<>();
                addressDetails.put("id", address.getId());
                addressDetails.put("address", address.getAddress());
                addressDetails.put("lat", address.getLat());
                addressDetails.put("lng", address.getLng());
                addressDetailsList.add(addressDetails);
            }
            return addressDetailsList;
        }
        return Collections.emptyList();
    }
}
