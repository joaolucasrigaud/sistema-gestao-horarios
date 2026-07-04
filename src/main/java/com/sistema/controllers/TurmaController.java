package com.sistema.controllers;

import com.sistema.models.Horario;
import com.sistema.models.Turma;
import com.sistema.repositories.HorarioRepository;
import com.sistema.repositories.TurmaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaRepository turmaRepository;
    private final HorarioRepository horarioRepository;

    public TurmaController(TurmaRepository turmaRepository, HorarioRepository horarioRepository) {
        this.turmaRepository = turmaRepository;
        this.horarioRepository = horarioRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todas as turmas")
    public ResponseEntity<List<Turma>> listar() {
        return ResponseEntity.ok(turmaRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma turma por id")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return turmaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastra uma turma")
    public ResponseEntity<Turma> salvar(@RequestBody Turma turma) {
        Turma salva = turmaRepository.save(turma);
        return ResponseEntity.created(URI.create("/api/turmas/" + salva.getId())).body(salva);
    }

    @GetMapping("/{id}/horarios")
    @Operation(summary = "Retorna os horários de uma turma")
    public ResponseEntity<Map<String, Object>> horariosDaTurma(@PathVariable Long id) {
        return turmaRepository.findById(id)
                .map(turma -> {
                    List<Horario> horarios = horarioRepository.findByTurmaId(id);
                    return ResponseEntity.ok(Map.of("turma", turma, "horarios", horarios));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
