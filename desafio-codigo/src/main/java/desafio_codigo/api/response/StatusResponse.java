package desafio_codigo.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusResponse {

    private String descricao;

}
