package desafio_codigo.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UnidadePenalRequest {


    @NotNull(message = "Nome Unidade Penal is Not Null")
    @NotEmpty(message = "Nome Unidade Penal is Not Empty")
    @Schema(description = "Nome da unidade penal", example = "Penitenciária do Distrito Federal I")
    private String nome;

    @NotNull(message = "Descrição Unidade Penal is Not Null")
    @NotEmpty(message = "Descrição Unidade Penal is Not Empty")
    @Schema(description = "Sigla da unidade penal", example = "PDF I")
    private String descricao;

    public void setNome(String nome) {

        this.nome = nome.toUpperCase();
    }

    public  void setDescricao(String descricao) {

        this.descricao = descricao.toUpperCase();
    }

}
