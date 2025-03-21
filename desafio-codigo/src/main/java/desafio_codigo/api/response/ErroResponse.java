package desafio_codigo.api.response;


import lombok.Data;

@Data
public class ErroResponse {
    private int statusCode;
    private String mensagem;

    public ErroResponse(int statusCode, String mensagem) {
        this.statusCode = statusCode;
        this.mensagem = mensagem;
    }
}

