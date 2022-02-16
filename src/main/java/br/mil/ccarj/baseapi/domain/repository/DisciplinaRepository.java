package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
