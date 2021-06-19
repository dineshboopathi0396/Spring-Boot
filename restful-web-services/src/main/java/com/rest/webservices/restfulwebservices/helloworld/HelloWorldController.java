package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller - handles http requests
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//GET
	//URI - /hello-world
	//method - "Hellow World"
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	//hellow-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hi Dinesh");
	}
	
	///hello-world/PathVariable/{name}
		@GetMapping(path = "/hello-world/PathVariable/{name}")
		public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
//			return new HelloWorldBean(String.format("Hello %s", name));
			return new HelloWorldBean("Hello " + name);
		}
		
		@GetMapping(path = "/hello-world-internationalized")
//		public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
			public String helloWorldInternationalized() {
			return messageSource.getMessage("good.morning.message", null, 
					LocaleContextHolder.getLocale());
		}

}
