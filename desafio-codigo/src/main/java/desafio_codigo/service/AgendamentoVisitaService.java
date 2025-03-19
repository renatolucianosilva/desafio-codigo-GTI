package desafio_codigo.service;

import desafio_codigo.api.request.AgendamentoVisitaCancelarRequest;
import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.exceptions.BadRequestException;
import desafio_codigo.mapper.AgendamentoVisitaMapper;
import desafio_codigo.modell.AgendamentoVisita;
import desafio_codigo.modell.Custodiado;
import desafio_codigo.repository.AgendamentoVisitaRepository;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AgendamentoVisitaService {

    private final AgendamentoVisitaRepository agendamentoRepository;
    private final CustodiadoService custodiadoService;
    private final VisitanteService visitanteService;
    private final StatusService statusService;
    private final AgendamentoVisitaMapper Mapper;

    public AgendamentoVisita createAgendamentoVisita (AgendamentoVisitaRequest agendamento) {

        return agendamentoRepository.save(
                Mapper.toAgendamentoVisita(agendamento)
                        .novoAgendamentoVisita(verificarCustodiadoHorario(agendamento.getDataHoraAgendamento(), agendamento.getIdCustodiado()),
                             visitanteService.findById(agendamento.getIdVisitante()),
                             statusService.buscarStatus(1L)));

    }

    public Page<AgendamentoVisita> listaAgendamentoVisita (Pageable pageable) {

        return agendamentoRepository.findAll(pageable);

    }

    public AgendamentoVisita buscarAgendamentoVisita (Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento Não Existe"));
    }

    public List<AgendamentoVisita> listAgendamentoCustodiado (String name ) {

        if (agendamentoRepository
                .findByCustodiadoNome(name).isEmpty()) throw new BadRequestException("Custodiado Não Encontrado");

        return agendamentoRepository.findByCustodiadoNome(name);
    }

    public List<AgendamentoVisita> listAgendamentoVisitante (String nome ) {

        if (agendamentoRepository
                .findByVisitanteNome(nome).isEmpty()) throw new BadRequestException("Visitante Não Encontrado");

        return agendamentoRepository.findByVisitanteNome(nome);
    }

    public List<AgendamentoVisita> listAgendamentoStatus (String status ) {

        if (agendamentoRepository
                .findByStatusDescricao(status).isEmpty()) throw new BadRequestException("Não há agendamento com status " + status);

        return agendamentoRepository.findByStatusDescricao(status);
    }

    public List<AgendamentoVisita> listAgendamentoData (String data, String hora ) {

            var agendamento = agendamentoRepository.findByDataHoraAgendamento(conversorDataHora(data, hora));

            if (agendamento.isEmpty()) throw new BadRequestException("Agendamento nao encontrado para data " + data + " e hora " + hora);

        return agendamento;

    }

    public AgendamentoVisita cancelarVisita (AgendamentoVisitaCancelarRequest cancelamento) {

        return agendamentoRepository.save(buscarAgendamentoVisita(cancelamento.getIdAgendamento())
                .cancelarVisita(statusService.buscarStatus(2L)));
    }

    public Custodiado verificarCustodiadoHorario (LocalDateTime dataHoraAgendamento, Long idCustodiado) {

        var custodiado = custodiadoService.findCustodiadoById(idCustodiado);

        var agendamentosVisita = agendamentoRepository.findByCustodiadoNome(custodiado.getNome());

       var agendamentoFilter = agendamentosVisita.stream()
                .filter( agendamento -> agendamento.getDataHoraAgendamento()
                        .equals(dataHoraAgendamento)).findAny();

        if(!agendamentoFilter.isEmpty()) throw new BadRequestException("Custodiado já possui agendamento para esse horario");

        return custodiado;
    }

    public LocalDateTime conversorDataHora(String data, String hora){

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate dataFormatada = LocalDate.parse(data, formatDate);

        LocalTime horaFormatada = LocalTime.parse(hora, formatTime);

        LocalDateTime dateTime = LocalDateTime.of(dataFormatada, horaFormatada);
        System.out.println(dateTime);


        return dateTime;
    }


}
