package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoDisciplinaRepository extends JpaRepository<ProcessoDisciplina, Long> {
}
