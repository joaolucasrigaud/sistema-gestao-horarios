package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    // Construtor padrão (sem argumentos)
    public Horario() {
    }

    // Construtor com argumentos (sem relacionamentos)
    public Horario(String diaDaSemana, LocalTime horaInicio, LocalTime horaFim) {
        this.diaDaSemana = diaDaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    // Construtor com argumentos (com relacionamentos)
    public Horario(String diaDaSemana, LocalTime horaInicio, LocalTime horaFim,
                   Turma turma, Disciplina disciplina, Professor professor) {
        this.diaDaSemana = diaDaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.turma = turma;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    // Construtor com todos os argumentos
    public Horario(Long id, String diaDaSemana, LocalTime horaInicio, LocalTime horaFim,
                   Turma turma, Disciplina disciplina, Professor professor) {
        this.id = id;
        this.diaDaSemana = diaDaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.turma = turma;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", diaDaSemana='" + diaDaSemana + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                ", turma=" + (turma != null ? turma.getCodigo() : "null") +
                ", disciplina=" + (disciplina != null ? disciplina.getNome() : "null") +
                ", professor=" + (professor != null ? professor.getNome() : "null") +
                '}';
    }
}
