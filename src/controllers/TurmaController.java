package controllers;

import models.Turma;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import repositories.TurmaRepository;

@Controller
public class TurmaController {

    private final TurmaRepository turmaRepository;

    public TurmaController(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @GetMapping("/turmas")
    public String listar(Model model) {
        model.addAttribute("turmas", turmaRepository.findAll());
        return "turmas/list";
    }

    @GetMapping("/turmas/novo")
    public String novo(Model model) {
        model.addAttribute("turma", new Turma());
        return "turmas/form";
    }

    @PostMapping("/turmas")
    public String salvar(@ModelAttribute Turma turma) {
        turmaRepository.save(turma);
        return "redirect:/turmas";
    }
}
