
package com.example.provafinal.repository;

import com.example.provafinal.entity.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository
        extends JpaRepository<Artigo, Long> {
}
