package desafio_codigo.service;

import desafio_codigo.modell.Visitante;
import desafio_codigo.repository.VisitanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitanteService {

    private VisitanteRepository visitanteRepository;

    public Visitante save(Visitante visitante) {

         return visitanteRepository.save(visitante);


    }

    public List<Visitante> findAll() {

        return visitanteRepository.findAll();
    }

    public Visitante findById(Long id) {
        return visitanteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Visitante Não Existe"));
    }



}
