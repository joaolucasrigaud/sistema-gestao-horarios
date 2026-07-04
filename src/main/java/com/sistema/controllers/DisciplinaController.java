package com.sistema.controllers;

import com.sistema.models.Disciplina;
import com.sistema.models.Professor;
import com.sistema.repositories.DisciplinaRepository;
import com.sistema.repositories.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaController(DisciplinaRepository disciplinaRepository,
                                ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todas as disciplinas")
    public ResponseEntity<List<Disciplina>> listar() {
        return ResponseEntity.ok(disciplinaRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma disciplina por id")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastra uma disciplina")
    public ResponseEntity<Disciplina> salvar(@RequestBody Disciplina disciplina) {
        // associa professor se vier apenas o id
        if (disciplina.getProfessor() != null && disciplina.getProfessor().getId() != null) {
            Professor p = professorRepository.findById(disciplina.getProfessor().getId()).orElse(null);
            disciplina.setProfessor(p);
        }
        Disciplina salva = disciplinaRepository.save(disciplina);
        return ResponseEntity.created(URI.create("/api/disciplinas/" + salva.getId())).body(salva);
    }
}
