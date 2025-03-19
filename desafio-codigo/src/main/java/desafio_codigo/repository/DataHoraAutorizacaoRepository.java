package desafio_codigo.repository;

import desafio_codigo.modell.DataHoraAutorizacao;
import desafio_codigo.modell.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataHoraAutorizacaoRepository extends JpaRepository<DataHoraAutorizacao, Long> {


}
