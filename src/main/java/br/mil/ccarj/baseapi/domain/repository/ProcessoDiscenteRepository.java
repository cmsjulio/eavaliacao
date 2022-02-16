package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.ProcessoDiscente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoDiscenteRepository extends JpaRepository<ProcessoDiscente, Long> {
}
