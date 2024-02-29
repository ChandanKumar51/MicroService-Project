package com.lcwd.user.service.repository;

import com.lcwd.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
