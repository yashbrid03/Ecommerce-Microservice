package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getUser(Integer userId) {
		try {
			User user = jdbcTemplate.queryForObject("select * from users where user_id=?",BeanPropertyRowMapper.newInstance(User.class),userId);
			return user;
		}catch(IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	public User getUserByUsername(String uname) {
		try {
			User user =  jdbcTemplate.queryForObject("select * from users where username = ?",BeanPropertyRowMapper.newInstance(User.class),uname);
			return user;
		}catch(IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	public int save(User user) {
		String sql = "INSERT INTO users (username, email, password_hash, first_name, last_name, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,
           user.getUsername(),
           user.getEmail(),
           user.getPasswordHash(),
           user.getFirstName(),
           user.getLastName(),
           user.getCreatedAt(),
           user.getUpdatedAt());
	}
	
	public int update(User user) {
		String sql = "UPDATE users SET username=?, email=?, password_hash=?, first_name=?, last_name=?, updated_at=? WHERE user_id = ?;";
		return jdbcTemplate.update(sql,
		           user.getUsername(),
		           user.getEmail(),
		           user.getPasswordHash(),
		           user.getFirstName(),
		           user.getLastName(),
		           user.getUpdatedAt(),
		           user.getUserId());
	}
}
