package desafio_codigo.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import desafio_codigo.modell.UnidadePenal;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustodiadoResponse {


    private Long idPessoa;

    private String nome;

    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String numeroProntuario;

    private String vulgo;

    private UnidadePenalResponse unidadePenal;

}