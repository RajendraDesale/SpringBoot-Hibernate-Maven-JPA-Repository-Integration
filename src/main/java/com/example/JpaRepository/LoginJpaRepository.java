package com.example.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.LoginModel;

public interface LoginJpaRepository extends JpaRepository<LoginModel, Long> {

	@Query("select l.username and l.password form login l where l.username=:username and l.password=:password")
	public boolean validateuser(@Param("username") String username,@Param("password") String password);
}
