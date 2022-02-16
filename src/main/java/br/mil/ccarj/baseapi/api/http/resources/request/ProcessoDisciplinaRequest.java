package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.DisciplinaResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ModeloDeAvaliacaoResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoAvaliativoResponse;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import io.swagger.annotations.Api;
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
public class ProcessoDisciplinaRequest {

    @ApiModelProperty(value = "Disciplina")
    private DisciplinaResponse disciplina;

    @ApiModelProperty(value = "Processo Avaliativo")
    private ProcessoAvaliativoResponse processoAvaliativo;

    @ApiModelProperty(value = "Modelo de Avaliacao")
    private ModeloDeAvaliacaoResponse modeloDeAvaliacao;
}
