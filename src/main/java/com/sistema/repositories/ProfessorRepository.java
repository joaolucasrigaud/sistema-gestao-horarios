package com.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
