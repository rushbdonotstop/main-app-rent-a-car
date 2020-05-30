package com.example.pricelist.service;

import com.example.pricelist.model.Pricelist;
import com.example.pricelist.repository.PricelistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricelistService {

    @Autowired
    private PricelistRepository pricelistRepository;

    public List<Pricelist> getAll() {
        return pricelistRepository.findAll();
    }
}
