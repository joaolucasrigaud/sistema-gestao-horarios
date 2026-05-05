package com.sistema.controllers;

import com.sistema.models.Disciplina;
import com.sistema.models.Professor;
import com.sistema.repositories.DisciplinaRepository;
import com.sistema.repositories.ProfessorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaController(DisciplinaRepository disciplinaRepository,
                                ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping("/disciplinas")
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "disciplinas/list";
    }

    @GetMapping("/disciplinas/novo")
    public String novo(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("professores", professorRepository.findAll());
        return "disciplinas/form";
    }

    @PostMapping("/disciplinas")
    public String salvar(@ModelAttribute Disciplina disciplina,
                         @RequestParam("professorId") Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        disciplina.setProfessor(professor);
        disciplinaRepository.save(disciplina);
        return "redirect:/disciplinas";
    }
}
