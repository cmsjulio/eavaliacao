package br.mil.ccarj.baseapi.api.http.resources.response;

import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.model.TipoDeProva;
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
public class ProcessoDisciplinaResponse {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Processo Avaliativo")
    private ProcessoAvaliativoResponse processoAvaliativo;

    @ApiModelProperty(value = "Processo Avaliativo")
    private DisciplinaResponse disciplina;

    @ApiModelProperty(value = "Processo Avaliativo")
    private ModeloDeAvaliacaoResponse modeloDeAvaliacao;

}
