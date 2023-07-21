package com.example.InSufficientFunds.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InSufficientFunds.Entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

	 Status findByName(String name);

	Optional<Status> findById(Long statusId);
}
