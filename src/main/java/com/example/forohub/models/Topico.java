package com.example.forohub.models;

import com.example.forohub.models.dto.topic.DTO_RegisterT;
import com.example.forohub.models.dto.topic.DTO_ResT;
import com.example.forohub.models.dto.topic.DTO_UpdateT;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    private Integer id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String autor;
    private String curso;
    private Integer respuestas;

    public Topico (DTO_RegisterT dto_t) {
        this.titulo = dto_t.titulo();
        this.mensaje = dto_t.mensaje();
        this.status = true;
        this.autor = dto_t.autor();
        this.curso = dto_t.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.respuestas = 0;
    }

    public DTO_ResT updateData (DTO_UpdateT dto_t) {
        if (dto_t.titulo() != null) this.titulo = dto_t.titulo();
        if (dto_t.mensaje() != null) this.mensaje = dto_t.mensaje();
        if (dto_t.autor() != null) this.autor = dto_t.autor();
        if (dto_t.curso() != null) this.curso = dto_t.curso();

        return new DTO_ResT(this.id, this.titulo, this.mensaje, this.autor, this.curso);
    }
}
