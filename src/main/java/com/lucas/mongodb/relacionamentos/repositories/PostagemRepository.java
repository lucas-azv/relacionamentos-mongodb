package com.lucas.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucas.mongodb.relacionamentos.models.Postagem;

public interface PostagemRepository extends MongoRepository<Postagem, String>{
    
}
