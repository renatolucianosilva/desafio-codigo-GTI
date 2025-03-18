package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustodiadoRequest {

    private String nome;
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String numeroProntuario;
    private String vulgo;
    private Long IdUnidadePenal;

}
