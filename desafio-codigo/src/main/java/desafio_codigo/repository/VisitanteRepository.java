package desafio_codigo.repository;

import desafio_codigo.modell.Custodiado;
import desafio_codigo.modell.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {



}
