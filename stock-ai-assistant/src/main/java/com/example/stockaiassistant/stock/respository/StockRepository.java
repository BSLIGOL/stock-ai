package com.example.stockaiassistant.stock.respository;

import com.example.stockaiassistant.stock.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository {

    /** 이름으로 종목 조회 */
    Optional<Stock> findByName(String name);
}
