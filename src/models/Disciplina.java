package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    // Construtor padrão (sem argumentos)
    public Disciplina() {
    }

    // Construtor com argumentos (sem professor)
    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // Construtor com argumentos (com professor)
    public Disciplina(String nome, int cargaHoraria, Professor professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    // Construtor com todos os argumentos
    public Disciplina(Long id, String nome, int cargaHoraria, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", professor=" + (professor != null ? professor.getNome() : "null") +
                '}';
    }
}
