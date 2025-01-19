package com.example.forohub.models.dto.topic;

import jakarta.validation.constraints.NotBlank;

public record DTO_RegisterT(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
