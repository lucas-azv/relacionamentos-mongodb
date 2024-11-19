package com.lucas.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucas.mongodb.relacionamentos.models.Curso;

public interface CursoRepository extends MongoRepository<Curso,String>{
    
}
