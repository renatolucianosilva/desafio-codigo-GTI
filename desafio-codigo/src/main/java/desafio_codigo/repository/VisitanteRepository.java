package desafio_codigo.repository;

import desafio_codigo.modell.Visitante;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

    Optional<Visitante> findByNome(String nome);

    Optional<Visitante> findByNomeAndCpf(String nome, String cpf);

    Optional<Visitante> findByCpf(String cpf);
}
