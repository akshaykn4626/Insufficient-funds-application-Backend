package com.example.InSufficientFunds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.InSufficientFunds.Entity.EventSource;
import com.example.InSufficientFunds.Entity.Status;

@Repository
public interface EventSourceRepository extends JpaRepository<EventSource, Long> {

	@Query("SELECT COUNT(verified) FROM EventSource where verified = null ")
	long findAllByVerified();
	
	@Query("SELECT COUNT(verified) FROM EventSource where verified = 'yes' ")
	long findAllByApproved();

	@Query("SELECT COUNT(verified) FROM EventSource where verified = 'no' ")
	long findAllByRejected();

	List<EventSource> findByStatus(Status status);

	List<EventSource> findByIdIn(List<Long> eventSourceIds);

	List<EventSource> findAllByStatus(Status status);

	long countByStatusName(String name);

	List<EventSource> findByUserIsNotNull();

	List<EventSource> findByStatusName(String name);
	

	

	


	

}
