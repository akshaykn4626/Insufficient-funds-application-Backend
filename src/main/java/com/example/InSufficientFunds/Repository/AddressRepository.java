package com.example.InSufficientFunds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InSufficientFunds.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
