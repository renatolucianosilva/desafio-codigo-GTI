package desafio_codigo.service;

import desafio_codigo.api.request.AgendamentoVisitaAlterarRequest;
import desafio_codigo.api.request.AgendamentoVisitaAlterarStatusRequest;
import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.config.FormatarCpf;
import desafio_codigo.exceptions.BadRequestException;
import desafio_codigo.modell.AgendamentoVisita;
import desafio_codigo.modell.Custodiado;
import desafio_codigo.modell.Visitante;
import desafio_codigo.repository.AgendamentoVisitaRepository;

import desafio_codigo.repository.DataHoraAutorizacaoRepository;
import lombok.AllArgsConstructor;
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
    private final DataHoraAutorizacaoRepository dataHoraAutorizacaoRepository;
    private final FormatarCpf formatarCpf;


    public AgendamentoVisita createAgendamentoVisita(AgendamentoVisitaRequest agendamento, String visitante, String custodiado) {

        verificacaoDataHorario(conversorDataHora(agendamento.getDataAgendamento(), agendamento.getHoraAgendamento()));

        verificarCustodiadoHorario(conversorDataHora(agendamento.getDataAgendamento(),
                        agendamento.getHoraAgendamento()),
                custodiadoService.findCustodiadoByNomeAndProntuario(custodiado, agendamento.getNumeroProntuario()).getIdPessoa());




        return agendamentoRepository.save(
                AgendamentoVisita.builder()
                        .custodiado(custodiadoService.findCustodiadoByNomeAndProntuario(custodiado, agendamento.getNumeroProntuario()))
                        .visitante(visitanteService.findByNomeAndCpf(visitante, formatarCpf.formatarCpf(agendamento.getVisitanteCpf())))
                        .dataHoraAgendamento(conversorDataHora(agendamento.getDataAgendamento(), agendamento.getHoraAgendamento()))
                        .status(statusService.buscarStatus(1L))
                        .build()
        );

    }

    public Page<AgendamentoVisita> listaAgendamentoVisita(Pageable pageable) {

        return agendamentoRepository.findAll(pageable);

    }

    public AgendamentoVisita buscarAgendamentoVisitaById(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento Não Existe"));
    }

    public List<AgendamentoVisita> listAgendamentoCustodiado(String name) {

        if (agendamentoRepository
                .findByCustodiadoNome(name).isEmpty()) throw new BadRequestException("Custodiado Não Encontrado");

        return agendamentoRepository.findByCustodiadoNome(name);
    }

    public List<AgendamentoVisita> listAgendamentoVisitante(String nome) {

        if (agendamentoRepository
                .findByVisitanteNome(nome).isEmpty()) throw new BadRequestException("Visitante Não Encontrado");

        return agendamentoRepository.findByVisitanteNome(nome);
    }

    public List<AgendamentoVisita> listAgendamentoStatus(String status) {

        if (agendamentoRepository
                .findByStatusDescricao(status).isEmpty())
            throw new BadRequestException("Não há agendamento com status " + status);

        return agendamentoRepository.findByStatusDescricao(status);
    }

    public List<AgendamentoVisita> listAgendamentoData(String data, String hora) {

        var agendamento = agendamentoRepository.findByDataHoraAgendamento(conversorDataHora(data, hora));

        if (agendamento.isEmpty())
            throw new BadRequestException("Agendamento nao encontrado para data " + data + " e hora " + hora);

        return agendamento;

    }

    public AgendamentoVisita cancelarAgendamentoVisita(AgendamentoVisitaAlterarStatusRequest cancelamento, String visitante, String custodiado) {


        var agendamentoUpdate = listAgendamentoData(cancelamento.getDataAgendamento(), cancelamento.getHoraAgendamento())
                .stream().filter(agendamento -> agendamento.getCustodiado().getNome().equalsIgnoreCase(custodiado)
                        && agendamento.getVisitante().getNome().equalsIgnoreCase(visitante)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento Não Encontrado"));

        return agendamentoUpdate.cancelarVisita(statusService.buscarStatus(2L));


    }

    public AgendamentoVisita realizarAgendamentoVisita(AgendamentoVisitaAlterarStatusRequest agendamento, String visitante, String custodiado) {


        var agendamentoUpdate = listAgendamentoData(agendamento.getDataAgendamento(), agendamento.getHoraAgendamento())
                .stream().filter(agend -> agend.getCustodiado().getNome().equalsIgnoreCase(custodiado)
                        && agend.getVisitante().getNome().equalsIgnoreCase(visitante)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento Não Encontrado"));

        return agendamentoUpdate.realizarVisita(statusService.buscarStatus(3L));


    }

    public AgendamentoVisita alterarDataHoraVisita(AgendamentoVisitaAlterarRequest novoAgendamento, String cpfVisitante, String data, String hora) {

        Visitante visitante = visitanteService.findByCpf(formatarCpf.formatarCpf(cpfVisitante));

        verificacaoDataHorario(conversorDataHora(novoAgendamento.getNovaData(), novoAgendamento.getNovoHorario()));

        AgendamentoVisita agendamento = agendamentoRepository.findByVisitanteNomeAndVisitanteCpfAndDataHoraAgendamento(
                        visitante.getNome(), visitante.getCpf(), conversorDataHora(data, hora))
                .orElseThrow(() -> new BadRequestException("Agendamento Não encontrado"));


        Custodiado custodiado = agendamento.getCustodiado();

        verificarCustodiadoHorario(conversorDataHora(novoAgendamento.getNovaData(), novoAgendamento.getNovoHorario()), custodiado.getIdPessoa());

        agendamento.alteraDataHoraVisita(conversorDataHora(novoAgendamento.getNovaData(), novoAgendamento.getNovoHorario()));

        return agendamentoRepository.save(agendamento);


    }

    public void verificarCustodiadoHorario(LocalDateTime dataHoraAgendamento, Long idCustodiado) {

        var custodiado = custodiadoService.findCustodiadoById(idCustodiado);

        var agendamentosVisita = agendamentoRepository.findByCustodiadoNome(custodiado.getNome());

        var agendamentoFilter = agendamentosVisita.stream()
                .filter(agendamento -> agendamento.getDataHoraAgendamento()
                        .equals(dataHoraAgendamento) && agendamento.getStatus().getIdStatus().equals(1L)
                ).findAny();

        if (agendamentoFilter.isPresent())
            throw new BadRequestException("Custodiado já possui agendamento para esse horario");

    }

    public void verificacaoDataHorario(LocalDateTime dataHoraAgendamento) {

       if (dataHoraAutorizacaoRepository.findByDataHoraAutorizado(dataHoraAgendamento).isEmpty())
           throw new BadRequestException("Data e Hora não Liberda para Agendamento");

    }

    public LocalDateTime conversorDataHora(String data, String hora) {

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate dataFormatada = LocalDate.parse(data, formatDate);

        LocalTime horaFormatada = LocalTime.parse(hora, formatTime);


        return LocalDateTime.of(dataFormatada, horaFormatada);
    }



}
