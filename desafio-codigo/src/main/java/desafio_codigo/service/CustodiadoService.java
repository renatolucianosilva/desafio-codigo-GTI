package desafio_codigo.service;


import desafio_codigo.api.request.CustodiadoRequest;
import desafio_codigo.mapper.CustodiadoMapper;
import desafio_codigo.modell.Custodiado;
import desafio_codigo.modell.UnidadePenal;
import desafio_codigo.repository.CustodiadoRepository;
import desafio_codigo.repository.UnidadePenalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CustodiadoService {

    private CustodiadoMapper Mapper;
    private CustodiadoRepository repository;
    private UnidadePenalService unidadePenalService;

    public Custodiado saveCustodiado(CustodiadoRequest custodiado) {

        var custodiadoToSave = Mapper.toCustodiado(custodiado);

        return repository.save(custodiadoToSave
                .createCustodiado(unidadePenalService
                        .findUnidadePenalById(custodiado.getIdUnidadePenal())));

    }

    public List<Custodiado> findAllCustodiados() {

        return repository.findAll();

    }

public Custodiado findCustodiadoById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Custodiado Não Encontrado"));
}
}
