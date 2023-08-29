package com.tc.brewery.repository;


import com.tc.brewery.entity.Address;
import com.tc.brewery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
////    UserWithAddressDTO findUserWithAddressByUser_Id(@Param("userId") Long userId);
//    List<Address> findByUser(User user);
//    Address findByUserId(Long userId); // Adjust the method name
//    List<Address> findAllByUserId(Long userId);

}
