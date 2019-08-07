package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Category;

@Repository
public interface ManyManyRepositoryForCategory extends JpaRepository<Category, Long>{

}
