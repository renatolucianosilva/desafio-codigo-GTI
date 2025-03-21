package desafio_codigo.controller;

import desafio_codigo.api.request.VisitanteRequest;
import desafio_codigo.api.response.VisitanteResponse;
import desafio_codigo.mapper.VisitanteMapper;
import desafio_codigo.service.VisitanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/visitantes")
@AllArgsConstructor
@Tag( name = "Visitantes")
public class VisitanteController {

    private final VisitanteService visitanteService;

    private final VisitanteMapper Mapper;

    @PostMapping
    @Operation(summary = "Cadastrar um novo visitante")
    @ApiResponse(responseCode = "201", description = "Visitante criado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitanteResponse.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<VisitanteResponse> createVisitante(@RequestBody VisitanteRequest visitante) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Mapper.toVisitanteResponse(visitanteService.save(Mapper.toVisitante(visitante))));

    }

    @GetMapping
    @Operation(summary = "Lista todos visitantes paginados")
    @ApiResponse(responseCode = "200", description = "Lista de visitantes retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitanteResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<Page<VisitanteResponse>> listarVisitantes(
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
                .body(visitanteService.listarVisitantes(pageable)
                        .map(Mapper::toVisitanteResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um visitante por ID")
    @ApiResponse(responseCode = "200", description = "Visitante encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitanteResponse.class)))
    @ApiResponse(responseCode = "404", description = "Visitante não encontrado", content = @Content())
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<VisitanteResponse> bucarVisitanteId(Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toVisitanteResponse(visitanteService.findById(id)));
    }


}
