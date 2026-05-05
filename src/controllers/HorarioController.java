package controllers;

import models.Disciplina;
import models.Horario;
import models.Professor;
import models.Turma;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.DisciplinaRepository;
import repositories.HorarioRepository;
import repositories.ProfessorRepository;
import repositories.TurmaRepository;

import java.time.LocalTime;

@Controller
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

    @GetMapping("/horarios")
    public String listar(Model model) {
        model.addAttribute("horarios", horarioRepository.findAll());
        return "horarios/list";
    }

    @GetMapping("/horarios/novo")
    public String novo(Model model) {
        model.addAttribute("horario", new Horario());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        model.addAttribute("professores", professorRepository.findAll());
        return "horarios/form";
    }

    @PostMapping("/horarios")
    public String salvar(@ModelAttribute Horario horario,
                         @RequestParam("turmaId") Long turmaId,
                         @RequestParam("disciplinaId") Long disciplinaId,
                         @RequestParam("professorId") Long professorId,
                         @RequestParam(value = "horaInicioStr", required = false) String horaInicioStr,
                         @RequestParam(value = "horaFimStr", required = false) String horaFimStr) {
        Turma turma = turmaRepository.findById(turmaId).orElse(null);
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElse(null);
        Professor professor = professorRepository.findById(professorId).orElse(null);

        if (horaInicioStr != null && !horaInicioStr.isEmpty()) {
            horario.setHoraInicio(LocalTime.parse(horaInicioStr));
        }
        if (horaFimStr != null && !horaFimStr.isEmpty()) {
            horario.setHoraFim(LocalTime.parse(horaFimStr));
        }

        horario.setTurma(turma);
        horario.setDisciplina(disciplina);
        horario.setProfessor(professor);

        horarioRepository.save(horario);
        return "redirect:/horarios";
    }

    @GetMapping("/horarios/por-professor")
    public String porProfessor(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("professores", professorRepository.findAll());
        if (id != null) {
            model.addAttribute("horarios", horarioRepository.findByProfessorId(id));
        }
        return "horarios/por-professor";
    }

    @GetMapping("/horarios/por-turma")
    public String porTurma(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("turmas", turmaRepository.findAll());
        if (id != null) {
            model.addAttribute("horarios", horarioRepository.findByTurmaId(id));
        }
        return "horarios/por-turma";
    }
}
