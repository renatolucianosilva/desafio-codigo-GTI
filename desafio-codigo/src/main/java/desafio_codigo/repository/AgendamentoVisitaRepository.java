package desafio_codigo.repository;

import desafio_codigo.modell.AgendamentoVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoVisitaRepository extends JpaRepository<AgendamentoVisita, Long> {


}
