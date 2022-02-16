package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.ProcessoDiscenteRequest;
import br.mil.ccarj.baseapi.api.http.resources.request.ProcessoDisciplinaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDiscenteResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.*;
import br.mil.ccarj.baseapi.domain.service.*;
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
@RequestMapping(value = "/processoDiscente")
public class ProcessoDiscenteController extends BaseController {

    private final ProcessoAvaliativoService processoAvaliativoService;
    private final DiscenteService discenteService;
    private final ProcessoDiscenteService service;
    private final ModelMapper modelMapper;

    public ProcessoDiscenteController(ProcessoDiscenteService service, ModelMapper modelMapper,
                                      DiscenteService disciplinaService, ProcessoAvaliativoService processoAvaliativoService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.discenteService = disciplinaService;
        this.processoAvaliativoService = processoAvaliativoService;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = ProcessoDiscenteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoDiscenteResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final ProcessoDiscente processoDiscente = service.findById(id);
        ProcessoDiscenteResponse response = modelMapper.map(processoDiscente, ProcessoDiscenteResponse.class);
        return respondOk(response);

    }

   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ProcessoDiscenteRequest processoDiscenteRequest) {
       Discente discente = discenteService.findById(processoDiscenteRequest.getDiscente().getId());
       ProcessoAvaliativo processoAvaliativo = processoAvaliativoService.findById(processoDiscenteRequest.getProcessoAvaliativo().getId());
       ProcessoDiscente request = modelMapper.map(processoDiscenteRequest, ProcessoDiscente.class);
       request.setDiscente(discente);
       request.setProcessoAvaliativo(processoAvaliativo);
       ProcessoDiscente created = service.create(request);
       ProcessoDiscenteResponse response = modelMapper.map(created, ProcessoDiscenteResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar ProcessoDiscente existente ", nickname = "updateProcessoDiscente", notes = "Atualiza ProcessoDiscente", response = ProcessoDiscenteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ProcessoDiscenteResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ProcessoDisciplina not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ProcessoDiscenteRequest request) {
        ProcessoDiscente data = modelMapper.map(request, ProcessoDiscente.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar ProcessoDiscente existente ", nickname = "deleteProcessoDiscente", notes = "deleta ProcessoDiscente", response = ProcessoDiscenteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoDiscenteResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ProcessoDiscente not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar ProcessoDiscente", nickname = "findAll", notes = "Multiple search parasm can be provided", response = ProcessoDiscente.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoDiscente.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ProcessoDiscente> processoDiscentePage = service.findAll(pageable);
        List<ProcessoDiscenteResponse> content = processoDiscentePage.stream()
                .map(item -> modelMapper.map(item, ProcessoDiscenteResponse.class))
                .collect(Collectors.toList());
        Page<ProcessoDiscenteResponse> processoDiscenteResponses = new PageImpl<>(content, pageable, processoDiscentePage.getTotalElements());
        return respondOk(processoDiscenteResponses);
    }

}
