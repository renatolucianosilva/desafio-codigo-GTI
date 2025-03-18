package desafio_codigo.service;


import desafio_codigo.exceptions.BadRequestException;
import desafio_codigo.modell.UnidadePenal;
import desafio_codigo.repository.UnidadePenalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnidadePenalService {

    private UnidadePenalRepository repository;

    public UnidadePenal saveUnidadePenal(UnidadePenal unidadePenal) {

        System.out.println("Service: " + unidadePenal.getNome());

        return repository.save(unidadePenal);

    }

    public List<UnidadePenal> findAllUnidadePenal() {


        return repository.findAll();
    }

    public UnidadePenal findUnidadePenalById(Long id) {
        System.out.println("Serviceunidade penal: " + id);
        return  repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade Penal Não Existe"));
    }
}
