package desafio_codigo.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadePenalResponse {

    @Schema(description = "ID Unidade Penal", example = "1")
    private Long idUnidade;

    @Schema(description = "Nome da unidade penal", example = "Penitenciária do Distrito Federal I")
    private String nome;

    @Schema(description = "Sigla da unidade penal", example = "PDF I")
    private String descricao;

}