package com.example.demo;
import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface customerRepo extends JpaRepository<Customer, Integer> {
	
	@Query(value="select id as customerId,customer_name as customerName, email as email from customer",nativeQuery=true)
    Collection<CustomerDto> getallCustomer();
}
