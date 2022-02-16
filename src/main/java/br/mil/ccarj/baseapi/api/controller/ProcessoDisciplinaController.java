package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.ProcessoDisciplinaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
import br.mil.ccarj.baseapi.domain.service.DisciplinaService;
import br.mil.ccarj.baseapi.domain.service.ModeloDeAvaliacaoService;
import br.mil.ccarj.baseapi.domain.service.ProcessoAvaliativoService;
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
@RequestMapping(value = "/processoDisciplina")
public class ProcessoDisciplinaController extends BaseController {

    private final ProcessoAvaliativoService processoAvaliativoService;
    private final DisciplinaService disciplinaService;
    private final ProcessoDisciplinaService service;
    private final ModelMapper modelMapper;

    public ProcessoDisciplinaController(ProcessoDisciplinaService service, ModelMapper modelMapper,
    DisciplinaService disciplinaService, ProcessoAvaliativoService processoAvaliativoService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.disciplinaService = disciplinaService;
        this.processoAvaliativoService = processoAvaliativoService;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = ProcessoDisciplinaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoDisciplinaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final ProcessoDisciplina processoDisciplina = service.findById(id);
        ProcessoDisciplinaResponse response = modelMapper.map(processoDisciplina, ProcessoDisciplinaResponse.class);
        return respondOk(response);

    }

   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ProcessoDisciplinaRequest processoDisciplinaRequest) {
       Disciplina disciplina = disciplinaService.findById(processoDisciplinaRequest.getDisciplina().getId());
       ProcessoAvaliativo processoAvaliativo = processoAvaliativoService.findById(processoDisciplinaRequest.getProcessoAvaliativo().getId());
       ProcessoDisciplina request = modelMapper.map(processoDisciplinaRequest, ProcessoDisciplina.class);
       request.setDisciplina(disciplina);
       request.setProcessoAvaliativo(processoAvaliativo);
       ProcessoDisciplina created = service.create(request);
       ProcessoDisciplinaResponse response = modelMapper.map(created, ProcessoDisciplinaResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar ProcessoDisciplina existente ", nickname = "updateProcessoDisciplina", notes = "Atualiza ProcessoDisciplina", response = ProcessoDisciplinaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ProcessoDisciplinaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ProcessoDisciplina not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ProcessoDisciplinaRequest request) {
        ProcessoDisciplina data = modelMapper.map(request, ProcessoDisciplina.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar ProcessoDisciplina existente ", nickname = "deleteProcessoDisciplina", notes = "deleta ProcessoDisciplina", response = ProcessoDisciplinaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoDisciplinaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ProcessoDisciplina not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar ProcessoDisciplinas", nickname = "findAll", notes = "Multiple search parasm can be provided", response = ProcessoDisciplina.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoDisciplina.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ProcessoDisciplina> processoDisciplinaPage = service.findAll(pageable);
        List<ProcessoDisciplinaResponse> content = processoDisciplinaPage.stream()
                .map(item -> modelMapper.map(item, ProcessoDisciplinaResponse.class))
                .collect(Collectors.toList());
        Page<ProcessoDisciplinaResponse> processoDisciplinaResponses = new PageImpl<>(content, pageable, processoDisciplinaPage.getTotalElements());
        return respondOk(processoDisciplinaResponses);
    }

}
