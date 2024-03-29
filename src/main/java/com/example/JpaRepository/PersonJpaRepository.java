package com.example.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.PersonModel;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonModel, Long> {
}
