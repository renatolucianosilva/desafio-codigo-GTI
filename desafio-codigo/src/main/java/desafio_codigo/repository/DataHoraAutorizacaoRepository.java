package desafio_codigo.repository;

import desafio_codigo.modell.DataHoraAutorizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DataHoraAutorizacaoRepository extends JpaRepository<DataHoraAutorizacao, Long> {

    List<DataHoraAutorizacao> findByDataHoraAutorizado(LocalDateTime dataHoraAutorizado);
}
