package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
}
