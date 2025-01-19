package com.example.forohub.models.dto.topic;

import com.example.forohub.models.Topico;

import java.time.LocalDateTime;

public record DTO_GetT(
        Integer id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        String autor,
        String curso
) {
    public DTO_GetT (Topico t){
        this(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getStatus(), t.getAutor(), t.getCurso());
    }
}
