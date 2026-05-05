package com.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.models.Horario;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByProfessorId(Long id);
    List<Horario> findByTurmaId(Long id);
}
