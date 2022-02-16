package br.mil.ccarj.baseapi.api.http.resources.response;

import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
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
public class DisciplinaResponse {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;
}
