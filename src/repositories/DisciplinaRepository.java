package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
