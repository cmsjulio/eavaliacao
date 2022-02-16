package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.TipoDeProvaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.TipoDeProvaResponse;
import br.mil.ccarj.baseapi.domain.model.Periodo;
import br.mil.ccarj.baseapi.domain.model.TipoDeProva;
import br.mil.ccarj.baseapi.domain.service.PeriodoService;
import br.mil.ccarj.baseapi.domain.service.TipoDeProvaService;
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
@RequestMapping(value = "/TipoDeProva")
public class TipoDeProvaController extends BaseController {

    private final TipoDeProvaService service;
    private final PeriodoService periodoService;
    private final ModelMapper modelMapper;

    public TipoDeProvaController(TipoDeProvaService service, ModelMapper modelMapper, PeriodoService periodoService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.periodoService = periodoService;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = TipoDeProvaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = TipoDeProvaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final TipoDeProva tipoDeProva = service.findById(id);
        TipoDeProvaResponse response = modelMapper.map(tipoDeProva, TipoDeProvaResponse.class);
        return respondOk(response);

    }


   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid TipoDeProvaRequest tipoDeProvaRequest) {
       Periodo periodo = periodoService.findById(tipoDeProvaRequest.getPeriodo().getId());
       TipoDeProva request = modelMapper.map(tipoDeProvaRequest, TipoDeProva.class);
       request.setPeriodo(periodo);
       TipoDeProva created = service.create(request);
       TipoDeProvaResponse response = modelMapper.map(created, TipoDeProvaResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar TipoDeProva existente ", nickname = "updateTipoDeProva", notes = "Atualiza TipoDeProva", response = TipoDeProvaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = TipoDeProvaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "TipoDeProva not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody TipoDeProvaRequest request) {
        TipoDeProva data = modelMapper.map(request, TipoDeProva.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar TipoDeProva existente ", nickname = "deleteTipoDeProva", notes = "deleta TipoDeProva", response = TipoDeProvaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = TipoDeProvaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "TipoDeProva not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar TipoDeProvas", nickname = "findAll", notes = "Multiple search parasm can be provided", response = TipoDeProva.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = TipoDeProva.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<TipoDeProva> tipoDeProvaPage = service.findAll(pageable);
        List<TipoDeProvaResponse> content = tipoDeProvaPage.stream()
                .map(item -> modelMapper.map(item, TipoDeProvaResponse.class))
                .collect(Collectors.toList());
        Page<TipoDeProvaResponse> tipoDeProvaResponses = new PageImpl<>(content, pageable, tipoDeProvaPage.getTotalElements());
        return respondOk(tipoDeProvaResponses);
    }

}
