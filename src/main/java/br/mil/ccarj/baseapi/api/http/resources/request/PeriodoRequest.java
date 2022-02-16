package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.ModeloDeAvaliacaoResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Modelo de Avaliacao")
    private ModeloDeAvaliacaoResponse modeloDeAvaliacao;
}
