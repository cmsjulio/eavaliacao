package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.DisciplinaResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoAvaliativoRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Descricao")
    private String descricao;

    @ApiModelProperty(value = "ProcessoDisciplina")
    private ProcessoDisciplinaResponse processoDisciplina;
}
