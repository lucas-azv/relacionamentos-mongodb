package com.lucas.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucas.mongodb.relacionamentos.models.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String>{
    
}
