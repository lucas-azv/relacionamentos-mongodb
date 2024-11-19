package com.lucas.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucas.mongodb.relacionamentos.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
}
