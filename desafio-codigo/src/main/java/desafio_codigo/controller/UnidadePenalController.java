package desafio_codigo.controller;

import desafio_codigo.api.request.UnidadePenalRequest;
import desafio_codigo.api.response.UnidadePenalResponse;
import desafio_codigo.api.response.VisitanteResponse;
import desafio_codigo.mapper.UnidadePenalMapper;
import desafio_codigo.service.UnidadePenalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/unidadespenais")
@AllArgsConstructor
@Tag( name = "Unidades Penais")
public class UnidadePenalController  {

    private UnidadePenalMapper Mapper;
    private UnidadePenalService service;


    @PostMapping
    @Operation(summary = "Cadastrar Unidade Penal")
    @ApiResponse(responseCode = "201", description = "Unidade Penal criada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadePenalResponse.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<UnidadePenalResponse> createUnidadePenal(@RequestBody @Valid UnidadePenalRequest unidadePenal) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Mapper.toUnidadePenalResponse(service.saveUnidadePenal(Mapper.toUnidadePenal(unidadePenal))));

    }


    @GetMapping
    @Operation(summary = "Lista todas Unidades Penais paginados")
    @ApiResponse(responseCode = "200", description = "Lista de Unidades Penais retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadePenalResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<Page<UnidadePenalResponse>> listarUnidadesPenais(

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
                .body(service.listarUnidadesPenais(pageable)
                        .map(Mapper::toUnidadePenalResponse));

    }



    @GetMapping("/{id}")
    @Operation(summary = "Retornar Unidade Penal pelo ID")
    @ApiResponse(responseCode = "200", description = "Unidade Penal retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadePenalResponse.class)))
    @ApiResponse(responseCode = "404", description = "Unidade Penal Não encontrada", content = @Content())
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<UnidadePenalResponse> listarUnidadePenalId(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toUnidadePenalResponse(service.findUnidadePenalById(id)));

    }

    @GetMapping("/filterDescricao")
    @Operation(summary = "Retornar Unidade Penal pela Descrição")
    @ApiResponse(responseCode = "200", description = "Unidade Penal retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadePenalResponse.class)))
    @ApiResponse(responseCode = "404", description = "Unidade Penal Não encontrada", content = @Content())
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<UnidadePenalResponse> unidadePenalDescricao(@RequestParam(defaultValue = "PDF I") String descricao) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toUnidadePenalResponse(service.findByDescricao(descricao)));

    }




}
