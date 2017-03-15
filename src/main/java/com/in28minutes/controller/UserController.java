package com.in28minutes.controller;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.dto.UserDetails;

@Controller
public class UserController {
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String addUser() {
		return "User/addUser";
	}
	
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public String addUser(@RequestParam int userId, @RequestParam String name, ModelMap model) {
		UserDetails user = new UserDetails();
		user.setUserId(userId);
		user.setName(name);
		user.setRemark("remark");
		user.setCreateDate(new Date());
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		model.put("userId", userId);
		model.put("userName", name);
		
		return "User/addUserSuccess";
	}
}
