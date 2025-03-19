package desafio_codigo.controller;

import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.api.response.AgendamentoVisitaResponse;
import desafio_codigo.mapper.AgendamentoVisitaMapper;
import desafio_codigo.service.AgendamentoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/agendamentos")
@AllArgsConstructor
public class AgendamentoVisitaController {

    private final AgendamentoService agendamentoService;
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


}
