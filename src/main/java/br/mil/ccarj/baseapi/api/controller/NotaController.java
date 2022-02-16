package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.NotaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.NotaResponse;
import br.mil.ccarj.baseapi.api.http.resources.response.TipoDeProvaResponse;
import br.mil.ccarj.baseapi.domain.model.Nota;
import br.mil.ccarj.baseapi.domain.model.TipoDeProva;
import br.mil.ccarj.baseapi.domain.service.NotaService;
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
@RequestMapping(value = "/Nota")
public class NotaController extends BaseController {

    private final NotaService service;
    private final ModelMapper modelMapper;
    private final TipoDeProvaService tipoDeProvaService;

    public NotaController(NotaService service, ModelMapper modelMapper, TipoDeProvaService tipoDeProvaService, TipoDeProvaService tipoDeProvaService1) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.tipoDeProvaService = tipoDeProvaService1;
    }

    @ApiOperation(value = "Buscar Nota por ID", nickname = "getNotaById", notes = "Returns a single Nota", response = TipoDeProvaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = TipoDeProvaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Nota not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Nota nota = service.findById(id);
        TipoDeProvaResponse response = modelMapper.map(nota, TipoDeProvaResponse.class);
        return respondOk(response);

    }


   @ApiOperation(value = "Criar nova Nota", nickname = "addNota", notes = "Criar Nota")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid NotaRequest notaRequest) {
       Long tipoDeProvaId = notaRequest.getTipoDeProva().getId();
       TipoDeProva tipoDeProva = tipoDeProvaService.findById(tipoDeProvaId);
       Nota request = modelMapper.map(notaRequest, Nota.class);
       Nota created = service.create(request);
       created.setTipoDeProva(tipoDeProva);
       NotaResponse response = modelMapper.map(created, NotaResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Nota existente ", nickname = "updateNota", notes = "Atualiza Nota", response = NotaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = NotaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Nota not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody NotaRequest request) {
        Nota data = modelMapper.map(request, Nota.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar Nota existente ", nickname = "deleteNota", notes = "deleta Nota", response = NotaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = NotaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Nota not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar Nota", nickname = "findAll", notes = "Multiple search parasm can be provided", response = Nota.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Nota.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Nota> notaPage = service.findAll(pageable);
        List<NotaResponse> content = notaPage.stream()
                .map(item -> modelMapper.map(item, NotaResponse.class))
                .collect(Collectors.toList());
        Page<NotaResponse> notaResponses = new PageImpl<>(content, pageable, notaPage.getTotalElements());
        return respondOk(notaResponses);
    }

}
