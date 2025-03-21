package desafio_codigo.controller;

import desafio_codigo.api.request.CustodiadoRequest;
import desafio_codigo.api.response.CustodiadoResponse;
import desafio_codigo.api.response.UnidadePenalResponse;
import desafio_codigo.api.response.VisitanteResponse;
import desafio_codigo.mapper.CustodiadoMapper;
import desafio_codigo.service.CustodiadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/custodiados")
@AllArgsConstructor
public class CustodiadoController {

    private CustodiadoMapper Mapper;
    private CustodiadoService service;

    @PostMapping
    @Operation(summary = "Cadastrar um novo custodiado")
    @ApiResponse(responseCode = "201", description = "Custodiado criado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustodiadoResponse.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<CustodiadoResponse> createCustodiado(@RequestBody @Valid CustodiadoRequest custodiado) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Mapper.toCustodiadoResponse(service.createCustodiado(custodiado)));

    }

    @GetMapping
    @Operation(summary = "Lista todos custodiados paginados")
    @ApiResponse(responseCode = "200", description = "Lista de custodiados retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustodiadoResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<Page<CustodiadoResponse>> findAllCustodiados(

            @Parameter(description = "Número da página",
                    schema = @Schema(type = "integer", defaultValue = "0"),
                    examples = @ExampleObject(name = "Página 0", value = "0")) int page,
            @Parameter(description = "Tamanho da página",
                    schema = @Schema(type = "integer", defaultValue = "10"),
                    examples = @ExampleObject(name = "Tamanho 10", value = "10")) int size,
            @Parameter(description = "Critério de ordenação",
                    schema = @Schema(type = "string", defaultValue = "nome"),
                    examples = @ExampleObject(name = "Ordenação por nome", value = "nome,asc"))
            String sort) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sort));

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.listarCustodiados(pageable)
                        .map(Mapper::toCustodiadoResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna Custodiado pelo ID")
    @ApiResponse(responseCode = "200", description = "Return Successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustodiadoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Custodiado Não encontrado", content = @Content())
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<CustodiadoResponse> findCustodiadoById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toCustodiadoResponse(service.findCustodiadoById(id)));
    }

    @GetMapping("/filterByNome")
    @Operation(summary = "Retorna Custodiado pelo Nome")
    @ApiResponse(responseCode = "200", description = "Return Successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustodiadoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Custodiado Não encontrado", content = @Content())
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<List<CustodiadoResponse>> findCustodiadoById(@Parameter(description = "Nome do Custodiado:")
                                                                     @RequestParam String nomeCustodiado) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toCustodiadoResponseList(service.findCustodiadoByNome(nomeCustodiado)));
    }


}
