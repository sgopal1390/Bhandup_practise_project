package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.OneEntity;

public interface BidirectionMappingExampleForOneToMAnySAveRepositoy extends JpaRepository<OneEntity, Long>{

}
