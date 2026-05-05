package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
