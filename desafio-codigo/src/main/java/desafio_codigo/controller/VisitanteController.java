package desafio_codigo.controller;

import desafio_codigo.api.request.VisitanteRequest;
import desafio_codigo.api.response.VisitanteResponse;
import desafio_codigo.mapper.VisitanteMapper;
import desafio_codigo.service.VisitanteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/visitantes")
@AllArgsConstructor
public class VisitanteController {

    private final VisitanteService visitanteService;

    private final VisitanteMapper Mapper;

    @PostMapping
    public ResponseEntity<VisitanteResponse> createVisitante(@RequestBody VisitanteRequest visitante) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Mapper.toVisitanteResponse(visitanteService.save(Mapper.toVisitante(visitante))));

    }

    @GetMapping
    public ResponseEntity<List<VisitanteResponse>> listarVisitantes() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toVisitanteResponseList(visitanteService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteResponse> bucarVisitanteId(Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toVisitanteResponse(visitanteService.findById(id)));
    }


}
