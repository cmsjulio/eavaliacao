package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoAvaliativoRepository extends JpaRepository<ProcessoAvaliativo, Long> {
}
