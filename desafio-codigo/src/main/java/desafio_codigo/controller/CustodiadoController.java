package desafio_codigo.controller;

import desafio_codigo.api.request.CustodiadoRequest;
import desafio_codigo.api.response.CustodiadoResponse;
import desafio_codigo.mapper.CustodiadoMapper;
import desafio_codigo.modell.Custodiado;
import desafio_codigo.service.CustodiadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
                .body(Mapper.toCustodiadoResponse(service.saveCustodiado(custodiado)));

    }

    @GetMapping
    public ResponseEntity<List<CustodiadoResponse>> findAllCustodiados(){

        return ResponseEntity.status(HttpStatus.OK)
                .body(Mapper.toCustodiadoResponseList(service.findAllCustodiados()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustodiadoResponse> findCustodiadoById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Mapper.toCustodiadoResponse(service.findCustodiadoById(id)));
    }




}
