package controllers;

import models.Professor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import repositories.ProfessorRepository;

@Controller
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping("/professores")
    public String listar(Model model) {
        model.addAttribute("professores", professorRepository.findAll());
        return "professores/list";
    }

    @GetMapping("/professores/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        return "professores/form";
    }

    @PostMapping("/professores")
    public String salvar(@ModelAttribute Professor professor) {
        professorRepository.save(professor);
        return "redirect:/professores";
    }
}
