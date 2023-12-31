package com.tc.brewery.repository;

import com.tc.brewery.entity.Address;
import com.tc.brewery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long userId);
//    List<Address> findAddressesByUserId(Long userId);

    // You can directly use the inherited findById method from JpaRepository
}
