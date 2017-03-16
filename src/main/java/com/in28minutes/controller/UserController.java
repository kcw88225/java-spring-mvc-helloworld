package com.in28minutes.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.dto.UserDetails;
import com.in28minutes.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/getUser", method = RequestMethod.GET)
	public String getUser() {		
		List<UserDetails> users = userService.getUserDetailsList(0, 5);

		for(UserDetails user: users) {
			System.out.println(user.getName());
		}
		
		return "User/getUser";
	}
	
	@RequestMapping(value="/addUser", method = RequestMethod.GET)
	public String addUser() {
		return "User/addUser";
	}
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public String addUser(@RequestParam String name, ModelMap model) {
		UserDetails userDetails = new UserDetails();
		userDetails.setName(name);
		userDetails.setRemark("remark");
		userDetails.setCreateDate(new Date());
		
		userService.addUserDetails(userDetails);
		
		model.put("userName", name);
		
		return "User/addUserSuccess";
	}
}
