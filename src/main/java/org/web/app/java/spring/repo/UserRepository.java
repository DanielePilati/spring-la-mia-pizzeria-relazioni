package org.web.app.java.spring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	 public Optional<User> findByUsername(String username);

}
