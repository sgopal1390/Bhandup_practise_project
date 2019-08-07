package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.TwoEntity;

@Repository
public interface BidirectionMappingExampleRepositoy extends JpaRepository<TwoEntity, Long> {

}
