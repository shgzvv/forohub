package com.example.forohub.controller;

import com.example.forohub.models.Topico;
import com.example.forohub.models.dto.topic.DTO_GetT;
import com.example.forohub.models.dto.topic.DTO_RegisterT;
import com.example.forohub.models.dto.topic.DTO_ResT;
import com.example.forohub.models.dto.topic.DTO_UpdateT;
import com.example.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DTO_ResT> registrarTopico(
            @RequestBody @Valid DTO_RegisterT dtoRegistrarTopico,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Topico topico = topicoRepository.save(new Topico(dtoRegistrarTopico));
        DTO_ResT dtoResponseTopico = new DTO_ResT(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor(), topico.getCurso());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(dtoResponseTopico);
    }

    @GetMapping
    public List<DTO_GetT> listarTopicos() {
        return topicoRepository.findAll().stream().map(DTO_GetT::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO_GetT> buscarTopicoPorId(@PathVariable Integer id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var data = new DTO_GetT(topico);
        return ResponseEntity.ok(data);
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<DTO_GetT> atualizarTopico(
            @PathVariable Integer id,
            @RequestBody @Valid DTO_UpdateT dtoUpdateT
    ) {
        Topico topico = topicoRepository.getReferenceById(id);
        var newData = topico.updateData(dtoUpdateT);
        return ResponseEntity.ok(new DTO_GetT(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Integer id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }


}
