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
public class TipoDeProvaResponse {

    @ApiModelProperty(value = "Nome")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

}
