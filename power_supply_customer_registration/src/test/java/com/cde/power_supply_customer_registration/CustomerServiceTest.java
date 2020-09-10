package com.cde.power_supply_customer_registration;

import com.cde.power_supply_customer_registration.entity.Customer;
import com.cde.power_supply_customer_registration.repository.CustomerRepository;
import com.cde.power_supply_customer_registration.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CustomerService.class)
public class CustomerServiceTest {

    @Autowired
    public CustomerService customerService;

    @MockBean
    public CustomerRepository customerRepository;

    DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    Date d1 = df.parse("12-10-1999");

    long i = 1234567891;

    Customer customer = new Customer(101, "ram", "ram", "ram123", "ADMIN" ,true , "f-05", "up", "india", "546890", "ram@gmail",
            "hjkl8765", i, d1, "asd5f678");

    public CustomerServiceTest() throws ParseException {
    }

    @Test
    public void saveCustomerDetailsTest(){
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assert.assertEquals(customer, customerService.saveCustomerDetails(customer));
    }

    @Test
    public void getAllCustomersTest(){
        Mockito.when(customerRepository.findAll()).thenReturn(Stream.of(customer).collect(Collectors.toList()));
        Assert.assertEquals(1, customerService.getAllCustomers().size());
    }

    @Test
    public void getCustomerByUsernameTest(){
        Mockito.when(customerRepository.findByUsername("ram")).thenReturn(java.util.Optional.ofNullable(customer));
        Assert.assertEquals(customer, customerService.getCustomerByUsername("ram"));
    }

    @Test
    public void updateCustomerTest(){
        Mockito.when(customerRepository.findByUsername("ram")).thenReturn(java.util.Optional.ofNullable(customer));
        customer.setPassword("123ram");
        Mockito.when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
        Assertions.assertThat(customerService.updateCustomerDetails("ram", customer)).isEqualTo(customer);
    }
}
