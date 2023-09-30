package com.cipfpmislata.basic_web.persistence.impl;

import com.cipfpmislata.basic_web.persistence.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    @Override
    public String getAll() {
        //conectar con la bbdd
        //ejectuar consulta select * from movies
        return "Listado de películas";
    }

    @Override
    public String find(int id) {
        return "Película por id";
    }
}
