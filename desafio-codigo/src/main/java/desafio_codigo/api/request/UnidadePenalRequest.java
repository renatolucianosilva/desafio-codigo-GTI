package desafio_codigo.api.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnidadePenalRequest {


    @NotNull(message = "Campo Nome da Unidade Obrigatorio")
    @NotEmpty(message = "Campo Nome da Unidade Obrigatorio")
    private String nome;

    @NotNull(message = "Campo Descrição da Unidade Obrigatorio")
    @NotEmpty(message = "Campo Descrição da Unidade Obrigatorio")
    private String descricao;

}
