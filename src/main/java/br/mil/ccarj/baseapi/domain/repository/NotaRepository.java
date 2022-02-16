package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}
