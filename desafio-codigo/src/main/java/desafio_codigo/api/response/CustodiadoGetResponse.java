package desafio_codigo.api.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustodiadoGetResponse {


    private Long idPessoa;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String numeroProntuario;

    private String vulgo;

    private UnidadePenalResponse unidadePenal;

}