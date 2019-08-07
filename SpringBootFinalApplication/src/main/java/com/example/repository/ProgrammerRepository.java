package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.Programmer;

public interface ProgrammerRepository extends JpaRepository<Programmer, Integer>{

}
