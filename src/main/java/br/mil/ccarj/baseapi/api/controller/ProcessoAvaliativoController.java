package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.ProcessoAvaliativoRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoAvaliativoResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.model.ProcessoDisciplina;
import br.mil.ccarj.baseapi.domain.service.DisciplinaService;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/processoAvaliativo")
public class ProcessoAvaliativoController extends BaseController {

    private final ProcessoDisciplinaService processoDisciplinaService;
    private final ProcessoAvaliativoService service;
    private final ModelMapper modelMapper;

    public ProcessoAvaliativoController(ProcessoAvaliativoService service, ModelMapper modelMapper,
                                        ProcessoDisciplinaService processoDisciplinaService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.processoDisciplinaService = processoDisciplinaService;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = ProcessoAvaliativoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoAvaliativoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final ProcessoAvaliativo processoAvaliativo = service.findById(id);
        ProcessoAvaliativoResponse response = modelMapper.map(processoAvaliativo, ProcessoAvaliativoResponse.class);
        return respondOk(response);

    }

   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ProcessoAvaliativoRequest processoAvaliativoRequest) {
//       ProcessoDisciplina processoDisciplina = processoDisciplinaService.findById(processoAvaliativoRequest.getProcessoDisciplina().getId());
       ProcessoAvaliativo request = modelMapper.map(processoAvaliativoRequest, ProcessoAvaliativo.class);
//       request.setProcessoDisciplina(processoDisciplina);
       ProcessoAvaliativo created = service.create(request);
       ProcessoAvaliativoResponse response = modelMapper.map(created, ProcessoAvaliativoResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar ProcessoAvaliativo existente ", nickname = "updateProcessoAvaliativo", notes = "Atualiza ProcessoAvaliativo", response = ProcessoAvaliativoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ProcessoAvaliativoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ProcessoAvaliativo not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ProcessoAvaliativoRequest request) {
        ProcessoAvaliativo data = modelMapper.map(request, ProcessoAvaliativo.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar ProcessoAvaliativo existente ", nickname = "deleteProcessoAvaliativo", notes = "deleta ProcessoAvaliativo", response = ProcessoAvaliativoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoAvaliativoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ProcessoAvaliativo not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar ProcessoAvaliativos", nickname = "findAll", notes = "Multiple search parasm can be provided", response = ProcessoAvaliativo.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoAvaliativo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ProcessoAvaliativo> processoAvaliativoPage = service.findAll(pageable);
        List<ProcessoAvaliativoResponse> content = processoAvaliativoPage.stream()
                .map(item -> modelMapper.map(item, ProcessoAvaliativoResponse.class))
                .collect(Collectors.toList());
        Page<ProcessoAvaliativoResponse> processoAvaliativoResponses = new PageImpl<>(content, pageable, processoAvaliativoPage.getTotalElements());
        return respondOk(processoAvaliativoResponses);
    }

}
