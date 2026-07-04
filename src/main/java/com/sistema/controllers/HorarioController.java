package com.sistema.controllers;

import com.sistema.models.Disciplina;
import com.sistema.models.Horario;
import com.sistema.models.Professor;
import com.sistema.models.Turma;
import com.sistema.repositories.DisciplinaRepository;
import com.sistema.repositories.HorarioRepository;
import com.sistema.repositories.ProfessorRepository;
import com.sistema.repositories.TurmaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioRepository horarioRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public HorarioController(HorarioRepository horarioRepository,
                             TurmaRepository turmaRepository,
                             DisciplinaRepository disciplinaRepository,
                             ProfessorRepository professorRepository) {
        this.horarioRepository = horarioRepository;
        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todos os horários")
    public ResponseEntity<List<Horario>> listar() {
        return ResponseEntity.ok(horarioRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um horário por id")
    public ResponseEntity<Horario> buscarPorId(@PathVariable Long id) {
        return horarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastra um horário")
    public ResponseEntity<Horario> salvar(@RequestBody Horario horario) {
        // extrai relacionamentos por id se fornecidos
        if (horario.getTurma() != null && horario.getTurma().getId() != null) {
            Turma t = turmaRepository.findById(horario.getTurma().getId()).orElse(null);
            horario.setTurma(t);
        }
        if (horario.getDisciplina() != null && horario.getDisciplina().getId() != null) {
            Disciplina d = disciplinaRepository.findById(horario.getDisciplina().getId()).orElse(null);
            horario.setDisciplina(d);
        }
        if (horario.getProfessor() != null && horario.getProfessor().getId() != null) {
            Professor p = professorRepository.findById(horario.getProfessor().getId()).orElse(null);
            horario.setProfessor(p);
        }

        if (horario.getHoraInicio() == null) horario.setHoraInicio(LocalTime.MIDNIGHT);
        if (horario.getHoraFim() == null) horario.setHoraFim(LocalTime.MIDNIGHT);

        Horario salvo = horarioRepository.save(horario);
        return ResponseEntity.created(URI.create("/api/horarios/" + salvo.getId())).body(salvo);
    }

    @GetMapping("/professores/{id}")
    @Operation(summary = "Lista horários por professor")
    public ResponseEntity<List<Horario>> porProfessor(@PathVariable Long id) {
        return ResponseEntity.ok(horarioRepository.findByProfessorId(id));
    }

    @GetMapping("/turmas/{id}")
    @Operation(summary = "Lista horários por turma")
    public ResponseEntity<List<Horario>> porTurma(@PathVariable Long id) {
        return ResponseEntity.ok(horarioRepository.findByTurmaId(id));
    }
}
