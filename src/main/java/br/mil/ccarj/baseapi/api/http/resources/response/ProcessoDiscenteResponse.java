package br.mil.ccarj.baseapi.api.http.resources.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoDiscenteResponse {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Processo Avaliativo")
    private ProcessoAvaliativoResponse processoAvaliativo;

    @ApiModelProperty(value = "Discente")
    private DiscenteResponse discente;



}
