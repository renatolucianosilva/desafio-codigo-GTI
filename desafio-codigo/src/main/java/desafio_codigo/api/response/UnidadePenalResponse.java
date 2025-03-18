package desafio_codigo.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadePenalResponse {

    private int idUnidade;
    private String nome;
    private String descricao;

}