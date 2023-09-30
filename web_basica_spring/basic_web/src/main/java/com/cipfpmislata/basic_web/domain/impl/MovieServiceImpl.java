package com.cipfpmislata.basic_web.domain.impl;

import com.cipfpmislata.basic_web.domain.MovieService;
import com.cipfpmislata.basic_web.persistence.MovieRepository;
import com.cipfpmislata.basic_web.persistence.impl.MovieRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String getAll(){
        return movieRepository.getAll();
    }

    public String find(int id){
        return movieRepository.find(id);
    }
}
