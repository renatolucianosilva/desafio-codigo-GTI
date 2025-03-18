package desafio_codigo.repository;

import desafio_codigo.modell.Custodiado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustodiadoRepository extends JpaRepository<Custodiado, Long> {


}
