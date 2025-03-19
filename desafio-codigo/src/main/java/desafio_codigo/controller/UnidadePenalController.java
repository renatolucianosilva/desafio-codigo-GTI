package desafio_codigo.controller;

import desafio_codigo.api.request.UnidadePenalRequest;
import desafio_codigo.api.response.UnidadePenalResponse;
import desafio_codigo.mapper.UnidadePenalMapper;
import desafio_codigo.service.UnidadePenalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/unidadespenais")
@AllArgsConstructor
public class UnidadePenalController {

    private UnidadePenalMapper Mapper;
    private UnidadePenalService service;

    @PostMapping
    public ResponseEntity<UnidadePenalResponse> createUnidadePenal(@RequestBody @Valid UnidadePenalRequest unidadePenal) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Mapper.toUnidadePenalResponse(service.saveUnidadePenal(Mapper.toUnidadePenal(unidadePenal))));

    }

    @GetMapping
    public ResponseEntity<Page<UnidadePenalResponse>> listarUnidadesPenais(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.listarUnidadesPenais(pageable)
                        .map(Mapper::toUnidadePenalResponse));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadePenalResponse> listarUnidadePenalId(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toUnidadePenalResponse(service.findUnidadePenalById(id)));

    }





}
