package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.DiscenteResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.DisciplinaResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoAvaliativoResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoDiscenteRequest {

    @ApiModelProperty(value = "Discente")
    private DiscenteResponse discente;

    @ApiModelProperty(value = "Processo Avaliativo")
    private ProcessoAvaliativoResponse processoAvaliativo;

//    @ApiModelProperty(value = "Modelo de Avaliacao")
//    private ModeloDeAvaliacaoResponse modeloDeAvaliacao;
}
