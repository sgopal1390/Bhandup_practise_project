package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bean.StockEntity;

@Repository
public interface ManyManyRepositoryForStock extends JpaRepository<StockEntity,Long> {
	
	@Query("select s from  StockEntity s join s.category  where s.stockId =:id")
	StockEntity getStockDetailsById(@Param ("id") Long id);

}
