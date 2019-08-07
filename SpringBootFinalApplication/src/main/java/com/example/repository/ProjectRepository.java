package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bean.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	
	@Query(value = "select p from Project p where proid =:id")
	public Project findByproid(@Param ("id") int id);

}
