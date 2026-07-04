package com.sistema.controllers;

import com.sistema.models.Horario;
import com.sistema.models.Professor;
import com.sistema.repositories.HorarioRepository;
import com.sistema.repositories.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final HorarioRepository horarioRepository;

    public ProfessorController(ProfessorRepository professorRepository, HorarioRepository horarioRepository) {
        this.professorRepository = professorRepository;
        this.horarioRepository = horarioRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todos os professores")
    public ResponseEntity<List<Professor>> listar() {
        return ResponseEntity.ok(professorRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um professor por id")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastra um professor")
    public ResponseEntity<Professor> salvar(@RequestBody Professor professor) {
        Professor salvo = professorRepository.save(professor);
        return ResponseEntity.created(URI.create("/api/professores/" + salvo.getId())).body(salvo);
    }

    @GetMapping("/{id}/horarios")
    @Operation(summary = "Retorna os horários de um professor")
    public ResponseEntity<Map<String, Object>> horariosDoProfessor(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(prof -> {
                    List<Horario> horarios = horarioRepository.findByProfessorId(id);
                    return ResponseEntity.ok(Map.of("professor", prof, "horarios", horarios));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
