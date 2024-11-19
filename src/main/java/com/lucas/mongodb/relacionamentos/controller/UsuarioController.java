package com.lucas.mongodb.relacionamentos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.mongodb.relacionamentos.models.Perfil;
import com.lucas.mongodb.relacionamentos.models.Postagem;
import com.lucas.mongodb.relacionamentos.models.Usuario;
import com.lucas.mongodb.relacionamentos.repositories.PerfilRepository;
import com.lucas.mongodb.relacionamentos.repositories.PostagemRepository;
import com.lucas.mongodb.relacionamentos.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    // Métodos para Usuario

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable("id") String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null); // Retorna null se não encontrar o usuário
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        // Verificar se o perfil está presente e não tem id
        if (usuario.getPerfil() != null && usuario.getPerfil().getId() == null) {
            Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
        }
        // Salva o usuário, incluindo o perfil associado
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            usuarioAtualizado.setNome(usuario.getNome());

            // Atualizando o perfil se enviado
            if (usuario.getPerfil() != null) {
                if (usuario.getPerfil().getId() == null) {
                    Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
                    usuarioAtualizado.setPerfil(perfilSalvo);
                } else {
                    usuarioAtualizado.setPerfil(usuario.getPerfil());
                }
            }

            return usuarioRepository.save(usuarioAtualizado);
        }
        return null; // Caso o usuário não exista
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") String id) {
        usuarioRepository.deleteById(id);
    }

    // Métodos para Perfil

    @PostMapping("/perfis")
    public Perfil createPerfil(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @GetMapping("/perfis")
    public List<Perfil> getAllPerfis() {
        return perfilRepository.findAll();
    }

    @GetMapping("/perfis/{id}")
    public Perfil getPerfilById(@PathVariable("id") String id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);
        return perfil.orElse(null);
    }

    @PutMapping("/perfis/{id}")
    public Perfil updatePerfil(@PathVariable("id") String id, @RequestBody Perfil perfil) {
        Optional<Perfil> perfilExistente = perfilRepository.findById(id);
        if (perfilExistente.isPresent()) {
            Perfil perfilAtualizado = perfilExistente.get();
            perfilAtualizado.setBio(perfil.getBio());
            perfilAtualizado.setAvatarUrl(perfil.getAvatarUrl());
            return perfilRepository.save(perfilAtualizado);
        }
        return null;
    }

    @DeleteMapping("/perfis/{id}")
    public void deletePerfil(@PathVariable("id") String id) {
        perfilRepository.deleteById(id);
    }

    @PostMapping("/postagens")
    public Postagem createPostagem(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    @GetMapping("/postagens")
    public List<Postagem> getAllPostagens() {
        return postagemRepository.findAll();
    }

    @GetMapping("/postagens/{id}")
    public Postagem getPostagemById(@PathVariable("id") String id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        return postagem.orElse(null);
    }

    @PutMapping("/postagens/{id}")
    public Postagem updatePostagem(@PathVariable("id") String id, @RequestBody Postagem postagem) {
        Optional<Postagem> postagemExistente = postagemRepository.findById(id);
        if (postagemExistente.isPresent()) {
            Postagem postagemAtualizada = postagemExistente.get();
            postagemAtualizada.setTitulo(postagem.getTitulo());
            postagemAtualizada.setConteudo(postagem.getConteudo());
            return postagemRepository.save(postagemAtualizada);
        }
        return null;
    }

    @DeleteMapping("/postagens/{id}")
    public void deletePostagem(@PathVariable("id") String id) {
        postagemRepository.deleteById(id);
    }
}
