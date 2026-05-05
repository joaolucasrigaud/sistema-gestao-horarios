package com.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
