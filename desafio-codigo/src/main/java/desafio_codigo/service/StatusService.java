package desafio_codigo.service;

import desafio_codigo.modell.Status;
import desafio_codigo.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class StatusService {

    private StatusRepository statusRepository;

    public Status buscarStatus(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status Not Found"));
    }

}
