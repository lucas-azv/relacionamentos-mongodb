package com.lucas.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucas.mongodb.relacionamentos.models.Estudante;

public interface EstudanteRepository extends MongoRepository<Estudante, String>{
    
}
