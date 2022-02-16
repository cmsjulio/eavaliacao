package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "ProcessoDisciplina")
    private ProcessoDisciplinaResponse processoDisciplina;

}
