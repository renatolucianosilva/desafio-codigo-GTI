package desafio_codigo.service;

import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.mapper.AgendamentoVisitaMapper;
import desafio_codigo.modell.AgendamentoVisita;
import desafio_codigo.repository.AgendamentoVisitaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoVisitaRepository agendamentoRepository;
    private final CustodiadoService custodiadoService;
    private final VisitanteService visitanteService;
    private final StatusService statusService;
    private final AgendamentoVisitaMapper Mapper;

    public AgendamentoVisita createAgendamentoVisita (AgendamentoVisitaRequest agendamento) {

        return agendamentoRepository.save(
                Mapper.toAgendamentoVisita(agendamento)
                .novoAgendamentoVisita(
                        custodiadoService.findCustodiadoById(agendamento.getIdCustodiado()),
                        visitanteService.findById(agendamento.getIdVisitante()),
                        statusService.buscarStatus(agendamento.getIdStatus())));



    }

    public Page<AgendamentoVisita> listaAgendamentoVisita (Pageable pageable) {

        return agendamentoRepository.findAll(pageable);

    }


}
