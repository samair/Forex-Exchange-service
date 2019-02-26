package com.webvidhi.microservices.forex.forexservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webvidhi.microservices.forex.forexservice.model.ExchangeValue;
import com.webvidhi.microservices.forex.forexservice.model.ExchangeValueRepository;

@EnableEurekaClient
@RestController
public class ForexController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	
	@GetMapping("/test")
	public String test() {
		//ApplicationPid pid = new ApplicationPid();
		return "Ola from Forex service :" ;
	}
	

}
