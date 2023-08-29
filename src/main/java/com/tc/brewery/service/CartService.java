package com.tc.brewery.service;

import com.tc.brewery.entity.Beer;
import com.tc.brewery.entity.Cart;
import com.tc.brewery.entity.CartItem;
import com.tc.brewery.entity.User;
import com.tc.brewery.repository.BeerRepository;
import com.tc.brewery.repository.CartRepository;
import com.tc.brewery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeerRepository beerRepository;


    public List<Cart> getCartsByUserId(Long userId) {
        return cartRepository.findCartsByUserId(userId);
    }

    @Transactional
    public boolean addCart(Long userId, Map<String, Object> cartDetails) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false; // User not found
        }

        // Extract cart properties from cartDetails
        Double totalAmount = Double.valueOf(cartDetails.get("totalAmount").toString());
        String modeOfPayment = cartDetails.get("modeOfPayment").toString();
        String modeOfDelivery = cartDetails.get("modeOfDelivery").toString();
        List<Map<String, Object>> cartItemsList = (List<Map<String, Object>>) cartDetails.get("cartItems");

        // Create a new Cart
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setModeOfPayment(modeOfPayment);
        newCart.setModeOfDelivery(modeOfDelivery);
        newCart.setTotalAmount(totalAmount);
        newCart.setTimestamp(LocalDateTime.now()); // Set the timestamp

        // Create and add CartItems to the new Cart
        List<CartItem> cartItems = new ArrayList<>();
        for (Map<String, Object> itemMap : cartItemsList) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(newCart); // Set the relationship

            // Set the beer_id
            Integer beerId = Integer.valueOf(itemMap.get("beerId").toString());
            Beer beer = beerRepository.findById(beerId).orElse(null);
            if (beer == null) {
                return false; // Beer not found
            }
            cartItem.setBeer(beer);

            // Set other attributes
            cartItem.setBeerQuantity(Integer.valueOf(itemMap.get("beerQuantity").toString()));
            cartItem.setBeerVolumeInMl(Double.valueOf(itemMap.get("beerVolumeInMl").toString()));
            cartItem.setBeerAmount(Double.valueOf(itemMap.get("beerAmount").toString()));
            cartItem.setAmountOfEachBeer(Double.valueOf(itemMap.get("amountOfEachBeer").toString()));

            cartItems.add(cartItem);
        }
        newCart.setCartItems(cartItems);

        // Add the new Cart to the user's cart list
        user.getCartList().add(newCart);

        // Save the updated user with the new Cart
        userRepository.save(user);

        return true;
    }
}




