package com.example.demo;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CustomerServiceTest {
	
    @Mock
    customerRepo repo;
    
    @Mock
    CustomerService service;

	@Test
	public void testGetCustomer() {
		
		assertNotNull(service.getCustomer());
		
	}
}
