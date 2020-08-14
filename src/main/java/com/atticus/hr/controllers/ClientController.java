package com.atticus.hr.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atticus.hr.Repository.ClientRepository;
import com.atticus.hr.domain.Client;
import com.atticus.hr.domain.PagerModel;

@Controller

public class ClientController {
	private static final int BUTTONS_TO_SHOW=3;
	private static final int INITIAL_PAGE=0;
	private static final int INITIAL_PAGE_SIZE=5;
	private static final int[] PAGE_SIZES= {5,10};
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public ModelAndView clientHome(@RequestParam("pageSize") Optional<Integer> pageSize, 
			@RequestParam("page") Optional<Integer> page) {
		if(clientRepository.count() != 0) {
			;
		}
		else {
			addToRepository();
		}
		ModelAndView modelAndView= new ModelAndView("client");
		int evalPageSize=pageSize.orElse(INITIAL_PAGE_SIZE);
		
		int evalPage=(page.orElse(0) < 1)? INITIAL_PAGE : page.get() - 1;
		
		Pageable pageable = PageRequest.of(evalPage, evalPageSize);
		Page<Client> clientList=clientRepository.findAll(pageable);
		
		PagerModel pager= new PagerModel(clientList.getTotalPages(),clientList.getNumber(),BUTTONS_TO_SHOW);
		
		modelAndView.addObject("clientlist", clientList);
		modelAndView.addObject("selectedPageSize",evalPageSize);
		modelAndView.addObject("pageSizes",PAGE_SIZES);
		modelAndView.addObject("pager",pager);
		
		return modelAndView;
	}
	/*@GetMapping("/clients")
	public ModelAndView getClients() {
		if(clientRepository.count() != 0) {
			;
		}
		else {
			addToRepository();
		}
		ModelAndView modelAndView= new ModelAndView("clients");
		int evalPageSize=INITIAL_PAGE_SIZE;
		
		int evalPage=INITIAL_PAGE;
		
		
		Pageable pageable = PageRequest.of(evalPage, evalPageSize);
		Page<Client> clientList=clientRepository.findAll(pageable);
		
		PagerModel pager= new PagerModel(clientList.getTotalPages(),clientList.getNumber(),BUTTONS_TO_SHOW);
		
		modelAndView.addObject("clientlist", clientList);
		modelAndView.addObject("pager",pager);
		
		return modelAndView;
	}*/

	private void addToRepository() {
		// TODO Auto-generated method stub
		Client widget=new Client();
		widget.setAddress("123 Fake Street");
		widget.setName("Widget Inc");
		widget.setCurrentInvoice(10000);
	
		
		clientRepository.save(widget);
		
		Client foo=new Client();
		
		foo.setAddress("456 Attorney Drive");
		foo.setCurrentInvoice(12300);
		foo.setName("Foo LLP");
		clientRepository.save(foo);
		
		Client bar= new Client();
		
		bar.setAddress("111 Bar Row");
		bar.setCurrentInvoice(16000);
		bar.setName("Bar and Food");
		clientRepository.save(bar);
		
		Client dog=new Client();
		dog.setName("Dog Food and Accessories");
		dog.setAddress("222 Dog Drive");
		dog.setCurrentInvoice(4000);
		clientRepository.save(dog);
		
		Client cat = new Client();
		cat.setAddress("333 Cat Court");
		cat.setCurrentInvoice(50000);
		cat.setName("Cat Food");
		clientRepository.save(cat);
		
		Client hat = new Client();
		hat.setAddress("444 Hat Drive");
		hat.setCurrentInvoice(60000);
		hat.setName("The Hat Shop");
		clientRepository.save(hat);
		//next client
		Client hatB = new Client();
		hatB.setAddress("445 Hat Drive");
		hatB.setCurrentInvoice(60000);
		hatB.setName("The Hat Shop B");
		clientRepository.save(hatB);
		//next client
		Client hatC = new Client();
		hatC.setAddress("446 Hat Drive");
		hatC.setCurrentInvoice(60000);
		hatC.setName("The Hat Shop C");
		clientRepository.save(hatC);
		//next client
		Client hatD = new Client();
		hatD.setAddress("446 Hat Drive");
		hatD.setCurrentInvoice(60000);
		hatD.setName("The Hat Shop D");
		clientRepository.save(hatD);
		//next client
		Client hatE = new Client();
		hatE.setAddress("447 Hat Drive");
		hatE.setCurrentInvoice(60000);
		hatE.setName("The Hat Shop E");
		clientRepository.save(hatE);
		
		Client hatF = new Client();
		hatF.setAddress("448 Hat Drive");
		hatF.setCurrentInvoice(60000);
		hatF.setName("The Hat Shop F");
		clientRepository.save(hatF);
		
	}
	

}
