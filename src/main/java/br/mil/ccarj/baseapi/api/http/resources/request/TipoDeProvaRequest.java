package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.PeriodoResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDeProvaRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Periodo")
    private PeriodoResponse periodo;
}
