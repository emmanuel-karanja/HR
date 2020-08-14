package com.atticus.hr.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.atticus.hr.Service.OrderService;
import com.atticus.hr.domain.Order;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public ResponseBodyEmitter orders() {
		ResponseBodyEmitter emitter=new ResponseBodyEmitter();
		ExecutorService executor=Executors.newSingleThreadExecutor();
		executor.execute(()->{
			   List<Order> orders=(List<Order>) orderService.findAll();
			   try {
				   for(Order order:orders) {
					   randomDelay();//the delay simulates a slow service execution
					   
					   emitter.send(order);
				   }
					   emitter.complete();
				   }
				   catch(Exception e) {
					   emitter.completeWithError(e);
				   }
			   });
		   executor.shutdown();
		   return emitter;
	}
	
	@GetMapping("/events")
	public SseEmitter getOrders() {
		SseEmitter emitter=new SseEmitter();
		ExecutorService executor=Executors.newSingleThreadExecutor();
		
		executor.execute(()->{
			List<Order> orders=(List<Order>) orderService.findAll();
			try {
				for(Order order: orders) {
					SseEventBuilder eventBuilder=SseEmitter.event();
					randomDelay();//the delay simulates a slow service execution. 
					   emitter.send(
							   eventBuilder.data(order)
							   .name("order-created")
							   .id(String.valueOf(order.hashCode())));
				}
				emitter.complete();
			}
			catch(IOException e) {
				emitter.completeWithError(e);
			}			
		});
		executor.shutdown();
		return emitter;
	}

private void randomDelay() {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(ThreadLocalRandom.current().nextInt(300));
	}
	catch(InterruptedException e) {
		Thread.currentThread().interrupt();
	}
}


}
