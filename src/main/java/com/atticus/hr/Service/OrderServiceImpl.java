package com.atticus.hr.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.atticus.hr.domain.Order;

@Service
public class OrderServiceImpl implements OrderService {
	private List<Order> orders=new ArrayList<>();
	
	@PostConstruct
	public void setup() {
		createOrders();
	}

	private void createOrders() {
		// TODO Auto-generated method stub
		for(int i=0;i<25;i++) {
			this.orders.add(createOrder());
		}
			
	}
	private Order createOrder() {
		// TODO Auto-generated method stub
		String uuid=UUID.randomUUID().toString();
		double amount=ThreadLocalRandom.current().nextDouble(1000.00d);
		return new Order(uuid,BigDecimal.valueOf(amount));
	}

	@Override
	public Iterable<Order> findAll(){
		return List.copyOf(orders);
	}
	
	

}
