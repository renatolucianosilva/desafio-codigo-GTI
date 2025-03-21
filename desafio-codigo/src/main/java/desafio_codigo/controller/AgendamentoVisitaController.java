package desafio_codigo.controller;

import desafio_codigo.api.request.AgendamentoVisitaAlterarRequest;
import desafio_codigo.api.request.AgendamentoVisitaCancelarRequest;
import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.api.response.AgendamentoVisitaResponse;
import desafio_codigo.mapper.AgendamentoVisitaMapper;
import desafio_codigo.service.AgendamentoVisitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/agendamentos")
@AllArgsConstructor
public class AgendamentoVisitaController {

    private final AgendamentoVisitaService agendamentoService;
    private final AgendamentoVisitaMapper Mapper;


    @PostMapping
    @Operation(summary = "Agendar Nova Visita")
    @ApiResponse(responseCode = "201", description = "Visita Agendada com Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<AgendamentoVisitaResponse> agendarNovaVisita(@RequestBody @Valid AgendamentoVisitaRequest agendamento,
                                                                       @RequestParam String nomeVisitante,
                                                                       @RequestParam String nomeCustodiado) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Mapper.toAgendamentoVisitaResponse(agendamentoService
                        .createAgendamentoVisita(agendamento, nomeVisitante, nomeCustodiado)));

    }

    @GetMapping
    @Operation(summary = "Lista de Todos Agendamentos paginados")
    @ApiResponse(responseCode = "200", description = "Agendamentos Pageable",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<Page<AgendamentoVisitaResponse>> listarAgendamentosVisita(
            @Parameter(description = "Número da página",
                    schema = @Schema(type = "integer", defaultValue = "0"),
                    examples = @ExampleObject(name = "Página 0", value = "0")) int page,
            @Parameter(description = "Tamanho da página",
                    schema = @Schema(type = "integer", defaultValue = "10"),
                    examples = @ExampleObject(name = "Tamanho 10", value = "10")) int size,
            @Parameter(description = "Critério de ordenação",
                    schema = @Schema(type = "string", defaultValue = "idAgendamento"),
                    examples = @ExampleObject(name = "Ordenação por Id", value = "idAgendamento"))
            String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return ResponseEntity.status(HttpStatus.OK)
                .body(agendamentoService.listaAgendamentoVisita(pageable)
                        .map(Mapper::toAgendamentoVisitaResponse));
    }

    @GetMapping("/filterByCustodiado")
    @Operation(summary = "Retorna lista de agendamentos por custodiado, todos os status")
    @ApiResponse(responseCode = "200", description = "Lista de Agendamentos por Custodiado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaCustodiado(@RequestParam(required = true) String custodiado) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoCustodiado(custodiado)));
    }

    @GetMapping("/filterByVisitante")
    @Operation(summary = "Retorna lista de agendamentos por visitante, todos os status")
    @ApiResponse(responseCode = "200", description = "Lista de Agendamentos por Visitante",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaVisitante(@RequestParam(required = true) String visitante) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoVisitante(visitante)));
    }

    @GetMapping("/filterByStatus")
    @Operation(summary = "Retorna lista de agendamentos por tipo de status")
    @ApiResponse(responseCode = "200", description = "Lista de Agendamentos por Status",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaStatus(@RequestParam(required = true) String status) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoStatus(status.toUpperCase())));
    }

    @GetMapping("/filterByDateTime")
    @Operation(summary = "Retorna lista de agendamentos por data e hora, todos os status")
    @ApiResponse(responseCode = "200", description = "Lista de Agendamentos por Data e Hora",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaDateTime(@RequestParam(required = true) String date, @RequestParam(required = true) String time) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoData(date, time)));
    }

    @PutMapping("/cancelamento")
    @Operation(summary = "Cancela um agendamento")
    @ApiResponse(responseCode = "200", description = "Agendamento cancelado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<AgendamentoVisitaResponse> cancelarVisita(@RequestBody @Valid AgendamentoVisitaCancelarRequest cancelamento,
                                                                    @RequestParam String nomeVisitante,
                                                                    @RequestParam String nomeCustodiado) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper
                        .toAgendamentoVisitaResponse
                                (agendamentoService.cancelarAgendamentoVisita
                                        (cancelamento, nomeVisitante, nomeCustodiado)));

    }

    @PutMapping("/alterarDataHora")
    @Operation(summary = "Alterar data de um agendamento")
    @ApiResponse(responseCode = "200", description = "Agendamento alterado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoVisitaResponse.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<AgendamentoVisitaResponse> alterarDataVisita(
            @RequestBody @Valid AgendamentoVisitaAlterarRequest dadosAlteracao,
            @Schema(name = "CPF Visitante", example = "XXX.XXX.XXX-XX")
            @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX ou XXXXXXXXXXX" )
            @Length(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
            @RequestParam String cpfVisitante,
                                                                    @RequestParam String data,
                                                                       @RequestParam String hora) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper
                        .toAgendamentoVisitaResponse
                                (agendamentoService.alterarDataHoraVisita
                                        (dadosAlteracao, cpfVisitante, data, hora)));

    }


}
