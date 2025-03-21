package desafio_codigo.repository;

import desafio_codigo.modell.Custodiado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustodiadoRepository extends JpaRepository<Custodiado, Long> {

    Optional<List<Custodiado>> findByNome(String nome);

    Optional<Custodiado> findCustodiadoByNomeAndNumeroProntuario(String nome, String numeroProntuario);

}
