package com.sistema.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String semestre;

    // Construtor padrão (sem argumentos)
    public Turma() {
    }

    // Construtor com argumentos
    public Turma(String codigo, String semestre) {
        this.codigo = codigo;
        this.semestre = semestre;
    }

    // Construtor com todos os argumentos
    public Turma(Long id, String codigo, String semestre) {
        this.id = id;
        this.codigo = codigo;
        this.semestre = semestre;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", semestre='" + semestre + '\'' +
                '}';
    }
}
