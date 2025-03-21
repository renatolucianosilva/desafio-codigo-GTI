package desafio_codigo.repository;

import desafio_codigo.exceptions.BadRequestException;
import desafio_codigo.modell.UnidadePenal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadePenalRepository extends JpaRepository<UnidadePenal, Long> {

    Optional<UnidadePenal> findByDescricao(String descricao) throws BadRequestException;
}
