package com.atticus.hr;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.atticus.hr.Service.OrderService;
import com.atticus.hr.controllers.OrderController;
import com.atticus.hr.domain.Order;
import com.sun.tools.javac.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService orderService;
	
	@Test
	public void foo() throws Exception{
		
		when(orderService.findAll())
		  .thenReturn(List.of(new Order("1234",BigDecimal.TEN)));
		
		MvcResult mvcResult=mockMvc.perform(get("/orders"))
				.andExpect(request().asyncStarted())
				.andDo(MockMvcResultHandlers.log())
				.andReturn();
		
		mockMvc.perform(asyncDispatch(mvcResult))
		.andDo(MockMvcResultHandlers.log())
		.andExpect(status().isOk())
		.andExpect(content().json("{\"id\":\"1234\",\"amount\":\10}"));
	}
	
	
	

}
