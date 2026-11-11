
package com.example.provafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "artigo")
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String emailCriador;

    @Column(nullable = false)
    public String tituloArtigo;

    @Column(nullable = false, columnDefinition = "TEXT")
    public String descricao;

    @Column(nullable = false)
    public Integer prioridade;

    @Column(nullable = false)
    public LocalDateTime dataCriacao;

    @PrePersist
    public void antesDeSalvar() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }
}
