package com.tc.brewery.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Beer {
    @Id
    private int id;
    private String name;
    private String description;
    private String image_url;

    private int abv;
    private int ibu;
    private String category;
    private String brewers_tips;
    private String ingredient_name;
    private String food_pairing;
    private String tagline;
    @Column(precision = 3, scale = 1)
    private BigDecimal manualratings;

    @OneToMany(mappedBy = "beer",cascade= CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "beer",cascade=CascadeType.ALL)
    @JsonIgnoreProperties("beer")
    private List<Pricing> pricings = new ArrayList<>();

//    @OneToMany(mappedBy = "beer",cascade=CascadeType.ALL)
//    @JsonIgnoreProperties("beer")
//    private List<CartItem> cartItems = new ArrayList<>();
    @Column(precision = 3, scale = 1) // Adjust precision and scale as needed
    private BigDecimal averageRating; // Store average rating as a BigDecimal

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }
    public Beer() {
    }

    public Beer(int id, String name, String description, String image_url, int abv, int ibu, String category, String brewers_tips, String ingredient_name, String food_pairing, String tagline, BigDecimal manualratings, List<Rating> ratings, List<Pricing> pricings, List<CartItem> cartItems, BigDecimal averageRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.abv = abv;
        this.ibu = ibu;
        this.category = category;
        this.brewers_tips = brewers_tips;
        this.ingredient_name = ingredient_name;
        this.food_pairing = food_pairing;
        this.tagline = tagline;
        this.manualratings = manualratings;
        this.ratings = ratings;
        this.pricings = pricings;
//        this.cartItems = cartItems;
        this.averageRating = averageRating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Pricing> getPricings() {
        return pricings;
    }

    public void setPricings(List<Pricing> pricings) {
        this.pricings = pricings;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }


    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getFood_pairing() {
        return food_pairing;
    }

    public BigDecimal getManualratings() {
        return manualratings;
    }

    public void setManualratings(BigDecimal manualratings) {
        this.manualratings = manualratings;
    }

//    public List<CartItem> getCartItems() {
//        return cartItems;
//    }
//
//    public void setCartItems(List<CartItem> cartItems) {
//        this.cartItems = cartItems;
//    }

    public void setFood_pairing(String food_pairing) {
        this.food_pairing = food_pairing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getAbv() {
        return abv;
    }

    public void setAbv(int abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrewers_tips() {
        return brewers_tips;
    }

    public void setBrewers_tips(String brewers_tips) {
        this.brewers_tips = brewers_tips;
    }

    @JsonGetter("ratings") // Custom serialization for ratings
    public List<Map<String, Object>> getRatingsInfo() {
        List<Map<String, Object>> ratingsInfo = new ArrayList<>();
        for (Rating rating : ratings) {
            Map<String, Object> info = new HashMap<>();
            info.put("id", rating.getId());
            info.put("rating", rating.getRating());
            info.put("review", rating.getReview());
            info.put("user", getUserInfo(rating.getUser())); // Extract user info
            ratingsInfo.add(info);
        }
        return ratingsInfo;
    }

    private Map<String, Object> getUserInfo(User user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("firstName", user.getFirstName()); // Include first name
        userInfo.put("lastName", user.getLastName());   // Include last name
        return userInfo;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", category='" + category + '\'' +
                ", brewers_tips='" + brewers_tips + '\'' +
                ", ingredient_name='" + ingredient_name + '\'' +
                ", food_pairing='" + food_pairing + '\'' +
                ", tagline='" + tagline + '\'' +
                ", manualratings=" + manualratings +
                ", ratings=" + ratings +
                ", pricings=" + pricings +
//                ", cartItems=" + cartItems +
                ", averageRating=" + averageRating +
                '}';
    }
}
