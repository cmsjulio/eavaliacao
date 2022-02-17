package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.DiscenteRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.DiscenteResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoDiscenteResponse;
import br.mil.ccarj.baseapi.domain.model.Discente;
import br.mil.ccarj.baseapi.domain.model.ProcessoDiscente;
import br.mil.ccarj.baseapi.domain.service.DiscenteService;
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
@RequestMapping(value = "/discente")
public class DiscenteController extends BaseController {

    private final DiscenteService service;
    private final ModelMapper modelMapper;

    public DiscenteController(DiscenteService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Buscar discente por ID", nickname = "getDiscenteById", notes = "Returna um Discente", response = DiscenteResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = DiscenteResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Discente not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Discente discente = service.findById(id);
        DiscenteResponse response = modelMapper.map(discente, DiscenteResponse.class);
        return respondOk(response);

    }

    @ApiOperation(value = "Criar novo discente", nickname = "addDiscente", notes = "Criar discente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid DiscenteRequest discenteRequest) {
        Discente request = modelMapper.map(discenteRequest, Discente.class);
        Discente created = service.create(request);
        DiscenteResponse response = modelMapper.map(created, DiscenteResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Discente existente ", nickname = "updateDiscente", notes = "Atualiza Discente", response = DiscenteResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "successful operation", response = DiscenteResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Discente not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody DiscenteRequest request) {
        Discente data = modelMapper.map(request, Discente.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar Discente existente ", nickname = "deleteDiscente", notes = "deleta Discente", response = DiscenteResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = DiscenteResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Discente not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar Discentes", nickname = "findAll", notes = "Multiple search parasm can be provided", response = Discente.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = Discente.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Discente> discentePage = service.findAll(pageable);
        List<DiscenteResponse> content = discentePage.stream().map(item -> modelMapper.map(item, DiscenteResponse.class)).collect(Collectors.toList());
        Page<DiscenteResponse> discenteResponses = new PageImpl<>(content, pageable, discentePage.getTotalElements());
        return respondOk(discenteResponses);
    }

    @ApiOperation(value = "Buscar disciplina de discente por ID", nickname = "getDisciplinaDeDiscenteById", notes = "Returna um Discente", response = ProcessoDiscenteResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = ProcessoDiscenteResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Discente not found")})

    @GetMapping(value = "/disciplinas/{id}")
    @ResponseBody
    public List<ProcessoDiscente> teste(@PathVariable(name = "id") Long id) {
        final Discente discente = service.findById(id);
        final List<ProcessoDiscente> processoDiscente = discente.getProcessoDiscente();
        return processoDiscente;

    }
}