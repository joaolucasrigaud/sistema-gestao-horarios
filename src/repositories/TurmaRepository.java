package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
