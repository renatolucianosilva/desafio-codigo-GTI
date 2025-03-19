package desafio_codigo.controller;

import desafio_codigo.api.request.CustodiadoRequest;
import desafio_codigo.api.response.CustodiadoResponse;
import desafio_codigo.mapper.CustodiadoMapper;
import desafio_codigo.service.CustodiadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/custodiados")
@AllArgsConstructor
public class CustodiadoController {

    private CustodiadoMapper Mapper;
    private CustodiadoService service;

    @PostMapping
    public ResponseEntity<CustodiadoResponse> createCustodiado(@RequestBody @Valid CustodiadoRequest custodiado){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Mapper.toCustodiadoResponse(service.createCustodiado(custodiado)));

    }

    @GetMapping
    public ResponseEntity<Page<CustodiadoResponse>> findAllCustodiados(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.listarCustodiados(pageable)
                        .map(Mapper::toCustodiadoResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustodiadoResponse> findCustodiadoById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toCustodiadoResponse(service.findCustodiadoById(id)));
    }




}
