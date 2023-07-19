package com.cipfpmislata.basic_web;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public String getAll() {
        return "Listado de todos los productos";
    }
}
