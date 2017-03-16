package com.in28minutes.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.dto.UserDetails;
import com.in28minutes.repository.GeneralRepository;

@Service
public class UserService {
	@Autowired
	GeneralRepository generalRepository;

	public List<UserDetails> getUserDetailsList(int skip, int take) {
		Session session = generalRepository.getSession();

		Criteria criteria = session.createCriteria(UserDetails.class).setFirstResult(skip).setMaxResults(take);

		List<UserDetails> users = criteria.list();

		generalRepository.closeSession(session);

		return users;
	}

	public UserDetails getUserDetails(int userId) {
		Session session = generalRepository.getSession();
		
		UserDetails user = session.get(UserDetails.class, userId);

		generalRepository.closeSession(session);

		return user;
	}

	public void addUserDetails(UserDetails user) {
		Session session = generalRepository.getSession();

		session.save(user);

		generalRepository.commitSession(session);
	}
}
