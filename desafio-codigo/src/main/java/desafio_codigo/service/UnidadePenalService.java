package desafio_codigo.service;


import desafio_codigo.modell.UnidadePenal;
import desafio_codigo.repository.UnidadePenalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UnidadePenalService {

    private UnidadePenalRepository repository;

    public UnidadePenal saveUnidadePenal(UnidadePenal unidadePenal) {

        return repository.save(unidadePenal);

    }

    public Page<UnidadePenal> listarUnidadesPenais(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public UnidadePenal findUnidadePenalById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade Penal Não Existe"));
    }

    public UnidadePenal findByDescricao(String descricao) {

        return repository.findByDescricao(descricao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade Penal Não Existe"));
    }
}
