package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	protected static Logger log=LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
    private KafkaTemplate<?, String> kafkaTemplate;

	@Autowired
	UserMapper userMapper;

	@RequestMapping
	public String index() {
		return "Hello User";
	}

	@RequestMapping(value = "/getMap")
	public Map<String, Object> getThisMap(String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("Name", "LYW");
		map.put("Message", msg);
		return map;
	}

//	@ResponseBody
	@RequestMapping(value = "/getUser/{name}", method = { RequestMethod.POST, RequestMethod.GET })
	public User getUser(@PathVariable String name) {

		User user = userMapper.findUserByName(name);
		// User user = new User();
		// user.setId(12L);
		// user.setName(name);
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsers/{page}/{pageSize}")
	public List<User> getUserList(@PathVariable Integer page, @PathVariable Integer pageSize) {
		if(page!= null && pageSize!= null){  
            PageHelper.startPage(page, pageSize);  
        } 
		return userMapper.getAllUsers();
	}

	@RequestMapping(value = "/addUser/{name}", method = { RequestMethod.POST, RequestMethod.GET })
	public void addUser(@PathVariable String name) {
		User user = new User();
		user.setName(name);
		userMapper.addUser(user);
		
		log.info(user.getId().toString());
	}

	@RequestMapping(value = "/addUserPost", method = { RequestMethod.POST })
	public void addUserPost(@RequestBody User user) {
		userMapper.addUser(user);
	}

	@Transactional
	@RequestMapping(value = "/updateUser/{id}/{name}")
	public void updateUser(@PathVariable Long id, @PathVariable String name) {
		User user = new User();
		user.setName(name);
		user.setId(id);
		userMapper.updateUser(user);
	}

	@RequestMapping(value = "/updateUserPost", method = { RequestMethod.POST })
	public void updateUserPost(@RequestBody User user) {
		userMapper.updateUser(user);
	}

	@RequestMapping(value = "/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) {
		userMapper.deleteUser(id);
	}
	
	@RequestMapping(value = "/sendUser/{name}")
	public void sendUser(@PathVariable String name) {
		User u = new User();
		u.setName(name);
		u.setId(1L);

		ObjectMapper m = new ObjectMapper();

        try {
			kafkaTemplate.send("test1", m.writeValueAsString(u));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
