package desafio_codigo.repository;

import desafio_codigo.exceptions.BadRequestException;
import desafio_codigo.modell.AgendamentoVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoVisitaRepository extends JpaRepository<AgendamentoVisita, Long> {

        List<AgendamentoVisita> findByCustodiadoNome(String custodiado) throws BadRequestException;

        List<AgendamentoVisita> findByVisitanteNome(String visitante) throws BadRequestException;

        List<AgendamentoVisita> findByStatusDescricao(String status) throws BadRequestException;

        List<AgendamentoVisita> findByDataHoraAgendamento(LocalDateTime dataHoraAgendamento);
}
