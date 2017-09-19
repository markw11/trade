package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  
@RequestMapping(value = "/user") 
public class UserController {

	 @RequestMapping  
	    public String index() {  
	        return "Hello BeiJing";  
	    }  
	  
	    @RequestMapping(value = "/getMap")  
	    public Map<String, Object> getThisMap(String msg) {  
	        Map<String, Object> map = new HashMap<>();  
	        map.put("Name", "LYW");  
	        map.put("Message", msg);  
	        return map;  
	    }  
	  
	    @RequestMapping(value = "/getUser/{name}",method = { RequestMethod.POST, RequestMethod.GET })  
	    public User getUser(@PathVariable String name) {  
	        User user = new User();  
	        user.setId(12L);  
	        user.setName(name);    
	        return user;  
	    }  
}
