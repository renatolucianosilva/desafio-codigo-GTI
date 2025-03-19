package desafio_codigo.controller;

import desafio_codigo.api.request.AgendamentoVisitaCancelarRequest;
import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.api.response.AgendamentoVisitaResponse;
import desafio_codigo.mapper.AgendamentoVisitaMapper;
import desafio_codigo.service.AgendamentoVisitaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("v1/agendamentos")
@AllArgsConstructor
public class AgendamentoVisitaController {

    private final AgendamentoVisitaService agendamentoService;
    private final AgendamentoVisitaMapper Mapper;

    @PostMapping
    public ResponseEntity<AgendamentoVisitaResponse> save(@RequestBody AgendamentoVisitaRequest agendamento) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Mapper.toAgendamentoVisitaResponse(agendamentoService.createAgendamentoVisita(agendamento)));

    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoVisitaResponse>> listarAgendamentosVisita(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(agendamentoService.listaAgendamentoVisita(pageable)
                        .map(Mapper::toAgendamentoVisitaResponse));
    }

    @GetMapping("/filterByCustodiado")
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaCustodiado(@RequestParam (required = true) String custodiado) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoCustodiado(custodiado)));
    }

    @GetMapping("/filterByVisitante")
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaVisitante(@RequestParam (required = true) String visitante) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoVisitante(visitante)));
    }

    @GetMapping("/filterByStatus")
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaStatus(@RequestParam (required = true) String status) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoStatus(status.toUpperCase())));
    }

    @GetMapping("/filterByDateTime")
    public ResponseEntity<List<AgendamentoVisitaResponse>> filterAgendamentoVisitaDateTime(@RequestParam (required = true) String date, @RequestParam (required = true) String time) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgedamentoVisitaResponseList(agendamentoService.listAgendamentoData(date, time)));
    }

    @PutMapping("cancelamento")
    public ResponseEntity<AgendamentoVisitaResponse> cancelarVisita(@RequestBody AgendamentoVisitaCancelarRequest cancelamento) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toAgendamentoVisitaResponse(agendamentoService.cancelarVisita(cancelamento)));

    }


}
