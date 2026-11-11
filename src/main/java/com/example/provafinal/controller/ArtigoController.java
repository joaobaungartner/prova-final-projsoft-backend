
package com.example.provafinal.controller;

import com.example.provafinal.dto.CriarArtigoRequest;
import com.example.provafinal.entity.Artigo;
import com.example.provafinal.repository.ArtigoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {

    private final ArtigoRepository repository;

    public ArtigoController(ArtigoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Artigo> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artigo cadastrar(
            @Valid @RequestBody CriarArtigoRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String email = jwt.getClaimAsString(
                "https://api.prova-final.local/email"
        );

        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Email ausente no token"
            );
        }

        Artigo artigo = new Artigo();

        artigo.emailCriador = email;
        artigo.tituloArtigo = request.tituloArtigo();
        artigo.descricao = request.descricao();
        artigo.prioridade = request.prioridade();

        return repository.save(artigo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Artigo não encontrado"
            );
        }

        repository.deleteById(id);
    }
}
