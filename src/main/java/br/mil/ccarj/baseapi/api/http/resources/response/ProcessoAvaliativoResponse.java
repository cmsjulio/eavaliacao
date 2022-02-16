package br.mil.ccarj.baseapi.api.http.resources.response;

import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoAvaliativoResponse {

    private Long id;

    private String nome;

    private String descricao;

}
