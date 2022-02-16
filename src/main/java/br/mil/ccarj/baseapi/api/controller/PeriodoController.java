package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.PeriodoRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.PeriodoResponse;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.model.Periodo;
import br.mil.ccarj.baseapi.domain.service.ModeloDeAvaliacaoService;
import br.mil.ccarj.baseapi.domain.service.PeriodoService;
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
@RequestMapping(value = "/Periodo")
public class PeriodoController extends BaseController {

    private final PeriodoService service;
    private final ModeloDeAvaliacaoService modeloDeAvaliacaoService;
    private final ModelMapper modelMapper;

    public PeriodoController(PeriodoService service, ModelMapper modelMapper, ModeloDeAvaliacaoService modeloDeAvaliacaoService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.modeloDeAvaliacaoService = modeloDeAvaliacaoService;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = PeriodoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = PeriodoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Periodo periodo = service.findById(id);
        PeriodoResponse response = modelMapper.map(periodo, PeriodoResponse.class);
        return respondOk(response);

    }


   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid PeriodoRequest periodoRequest) {
       ModeloDeAvaliacao modeloDeAvaliacao = modeloDeAvaliacaoService.findById(periodoRequest.getModeloDeAvaliacao().getId());
       Periodo request = modelMapper.map(periodoRequest, Periodo.class);
       request.setModeloDeAvaliacao(modeloDeAvaliacao);
       Periodo created = service.create(request);
       PeriodoResponse response = modelMapper.map(created, PeriodoResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Periodo existente ", nickname = "updatePeriodo", notes = "Atualiza Periodo", response = PeriodoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = PeriodoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Periodo not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody PeriodoRequest request) {
        Periodo data = modelMapper.map(request, Periodo.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar Periodo existente ", nickname = "deletePeriodo", notes = "deleta Periodo", response = PeriodoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = PeriodoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Periodo not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar Periodos", nickname = "findAll", notes = "Multiple search parasm can be provided", response = Periodo.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Periodo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Periodo> periodoPage = service.findAll(pageable);
        List<PeriodoResponse> content = periodoPage.stream()
                .map(item -> modelMapper.map(item, PeriodoResponse.class))
                .collect(Collectors.toList());
        Page<PeriodoResponse> periodoResponses = new PageImpl<>(content, pageable, periodoPage.getTotalElements());
        return respondOk(periodoResponses);
    }

}
