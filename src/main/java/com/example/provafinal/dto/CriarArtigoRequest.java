
package com.example.provafinal.dto;

import jakarta.validation.constraints.*;

public record CriarArtigoRequest(
        @NotBlank String tituloArtigo,
        @NotBlank String descricao,
        @NotNull @Min(1) @Max(3) Integer prioridade
) {}
