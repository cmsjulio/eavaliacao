package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoAvaliativoResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDeAvaliacaoRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Sigla")
    private String sigla;

    @ApiModelProperty(value = "Descricao")
    private String descricao;

    @ApiModelProperty(value = "ProcessoDisciplina")
    private ProcessoDisciplinaResponse processoDisciplina;
}
