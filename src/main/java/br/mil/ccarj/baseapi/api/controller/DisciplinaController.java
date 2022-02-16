package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.DisciplinaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.DisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.service.DisciplinaService;
import br.mil.ccarj.baseapi.domain.service.ProcessoAvaliativoService;
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
@RequestMapping(value = "/disciplina")
public class DisciplinaController extends BaseController {
    
    private final DisciplinaService service;
    private final ModelMapper modelMapper;

    public DisciplinaController(DisciplinaService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = DisciplinaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = DisciplinaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Disciplina disciplina = service.findById(id);
        DisciplinaResponse response = modelMapper.map(disciplina, DisciplinaResponse.class);
        return respondOk(response);

    }

   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid DisciplinaRequest disciplinaRequest) {
       Disciplina request = modelMapper.map(disciplinaRequest, Disciplina.class);
       Disciplina created = service.create(request);
       DisciplinaResponse response = modelMapper.map(created, DisciplinaResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Disciplina existente ", nickname = "updateDisciplina", notes = "Atualiza Disciplina", response = DisciplinaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = DisciplinaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Disciplina not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody DisciplinaRequest request) {
        Disciplina data = modelMapper.map(request, Disciplina.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar Disciplina existente ", nickname = "deleteDisciplina", notes = "deleta Disciplina", response = DisciplinaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = DisciplinaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Disciplina not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar Disciplinas", nickname = "findAll", notes = "Multiple search parasm can be provided", response = Disciplina.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Disciplina.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Disciplina> disciplinaPage = service.findAll(pageable);
        List<DisciplinaResponse> content = disciplinaPage.stream()
                .map(item -> modelMapper.map(item, DisciplinaResponse.class))
                .collect(Collectors.toList());
        Page<DisciplinaResponse> disciplinaResponses = new PageImpl<>(content, pageable, disciplinaPage.getTotalElements());
        return respondOk(disciplinaResponses);
    }

}
