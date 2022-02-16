package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.ModeloDeAvaliacaoRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.ModeloDeAvaliacaoResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
import br.mil.ccarj.baseapi.domain.service.ModeloDeAvaliacaoService;
import br.mil.ccarj.baseapi.domain.service.ProcessoDisciplinaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/modeloDeAvaliacao")
public class ModeloDeAvaliacaoController extends BaseController {

    private final ProcessoDisciplinaService processoDisciplinaService;
    private final ModeloDeAvaliacaoService service;
    private final ModelMapper modelMapper;

    public ModeloDeAvaliacaoController(ModeloDeAvaliacaoService service, ModelMapper modelMapper,
                                       ProcessoDisciplinaService processoDisciplinaService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.processoDisciplinaService = processoDisciplinaService;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = ModeloDeAvaliacaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ModeloDeAvaliacaoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final ModeloDeAvaliacao modeloDeAvaliacao = service.findById(id);
        ModeloDeAvaliacaoResponse response = modelMapper.map(modeloDeAvaliacao, ModeloDeAvaliacaoResponse.class);
        return respondOk(response);

    }


    @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ModeloDeAvaliacaoRequest modeloDeAvaliacaoRequest) {
//       ProcessoDisciplina processoDisciplina = processoDisciplinaService.findById(modeloDeAvaliacaoRequest.getProcessoDisciplina().getId());
        ModeloDeAvaliacao request = modelMapper.map(modeloDeAvaliacaoRequest, ModeloDeAvaliacao.class);
//       request.setProcessoDisciplina(processoDisciplina);
        ModeloDeAvaliacao created = service.create(request);
        ModeloDeAvaliacaoResponse response = modelMapper.map(created, ModeloDeAvaliacaoResponse.class);
        return respondCreated(response);
    }

    @ApiOperation(value = "Atualizar ModeloDeAvaliacao existente ", nickname = "updateModeloDeAvaliacao", notes = "Atualiza ModeloDeAvaliacao", response = ModeloDeAvaliacaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ModeloDeAvaliacaoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ModeloDeAvaliacao not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ModeloDeAvaliacaoRequest request) {
        ModeloDeAvaliacao data = modelMapper.map(request, ModeloDeAvaliacao.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar ModeloDeAvaliacao existente ", nickname = "deleteModeloDeAvaliacao", notes = "deleta ModeloDeAvaliacao", response = ModeloDeAvaliacaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ModeloDeAvaliacaoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ModeloDeAvaliacao not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar ModeloDeAvaliacaos", nickname = "findAll", notes = "Multiple search parasm can be provided", response = ModeloDeAvaliacao.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ModeloDeAvaliacao.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ModeloDeAvaliacao> modeloDeAvaliacaoPage = service.findAll(pageable);
        List<ModeloDeAvaliacaoResponse> content = modeloDeAvaliacaoPage.stream()
                .map(item -> modelMapper.map(item, ModeloDeAvaliacaoResponse.class))
                .collect(Collectors.toList());
        Page<ModeloDeAvaliacaoResponse> modeloDeAvaliacaoResponses = new PageImpl<>(content, pageable, modeloDeAvaliacaoPage.getTotalElements());
        return respondOk(modeloDeAvaliacaoResponses);
    }

}
