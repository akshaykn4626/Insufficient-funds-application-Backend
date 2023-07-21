package com.example.InSufficientFunds.demo.RepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.InSufficientFunds.Entity.EventSource;
import com.example.InSufficientFunds.Entity.Status;
import com.example.InSufficientFunds.Entity.User;
import com.example.InSufficientFunds.Repository.EventSourceRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EventSourceRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventSourceRepository eventSourceRepository;

    private EventSource eventSource;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUserName("testuser");
        user.setPassword("password123");

        Status status = new Status();
        status.setName("testStatus");

        eventSource = new EventSource();
        eventSource.setBusinessKey("businessKey1");
        eventSource.setApplication("A2K");
        eventSource.setComments("comment1");
        eventSource.setTransactionCurrency("USD");
        eventSource.setTrasactionAmount(600.0);
        eventSource.setAmountInMur(200.0);
        eventSource.setDebitAccountNumber("1234567890");
        eventSource.setAccountShortName("Saving");
        eventSource.setDebitAccountCcy("EUR");
        eventSource.setPaymentDetails1("abc");
        eventSource.setPaymentDetails2("jhi");
        eventSource.setPaymentDetails3("klm");
        eventSource.setPaymentDetails4("def");
        eventSource.setVerified("yes");
        eventSource.setDiscrepancyReason("Not Sufficient");
        eventSource.setCreatedBy("akshay");
        eventSource.setCreatedOn(user.getCreationDate());
        eventSource.setLastModifiedBy("akshay");
        eventSource.setUpdatedOn(user.getLastModifiedDate());
        eventSource.setCustomerNumber("1234456");
        eventSource.setCustomerName("akshay");
        eventSource.setAccountOfficer("abc");
        eventSource.setAltAccountOfficer("def");
        eventSource.setOverride("yes");
        eventSource.setBuName("rajesh");
        eventSource.setAmountInDebitAccountCcy(311191);
        eventSource.setDebitAccountBalance(400000);
        eventSource.setBeneficiaryAccountNumber("000446534634");
        eventSource.setBeneficiaryName("Rajesh");
        eventSource.setBeneficiaryBankName("SBI");
        eventSource.setBeneficiaryBankSwiftCode("4465");
        eventSource.setAggredRate(10);
        eventSource.setStatus(status);
        eventSource.setUser(user);

        entityManager.persist(user);
        entityManager.persist(status);
        entityManager.persist(eventSource);
        entityManager.flush();
    }

    @Test
    @DisplayName("Find by status test")
    public void shouldFindByStatus() {
        // when - action and behaviour that we are going to test
        List<EventSource> found = eventSourceRepository.findByStatus(eventSource.getStatus());

        // then - verify the result and output using assert statements
        assertThat(found.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Count by status name test")
    public void shouldCountByStatusName() {
        // when - action and behaviour that we are going to test
        long count = eventSourceRepository.countByStatusName(eventSource.getStatus().getName());

        // then - verify the result and output using assert statements
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find by user is not null test")
    public void shouldFindByUserIsNotNull() {
        // when - action and behaviour that we are going to test
        List<EventSource> found = eventSourceRepository.findByUserIsNotNull();

        // then - verify the result and output using assert statements
        assertThat(found.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find by status name test")
    public void shouldFindByStatusName() {
        // when - action and behaviour that we are going to test
        List<EventSource> found = eventSourceRepository.findByStatusName(eventSource.getStatus().getName());

        // then - verify the result and output using assert statements
        assertThat(found.size()).isGreaterThan(0);
    }
}
