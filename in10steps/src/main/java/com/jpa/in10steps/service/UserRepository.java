package com.jpa.in10steps.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.in10steps.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
