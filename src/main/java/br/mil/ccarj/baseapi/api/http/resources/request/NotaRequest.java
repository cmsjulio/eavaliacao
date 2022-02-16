package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.PeriodoResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.TipoDeProvaResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaRequest {

    @ApiModelProperty(value = "Nota")
    private Long nota;

    @ApiModelProperty(value = "Tipo de Prova")
    private TipoDeProvaResponse tipoDeProva;

}
