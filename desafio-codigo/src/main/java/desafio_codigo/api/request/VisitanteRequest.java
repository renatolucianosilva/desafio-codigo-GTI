package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class VisitanteRequest {
    @NotNull(message = "Nome is Not Null")
    @NotEmpty(message = "Nome  is Not Empty")
    @Schema(description = "Nome", example = "Luiz Carlos da Silva Santos")
    private String nome;

    @NotNull(message = "CPF is Not Null")
    @NotEmpty(message = "CPF  is Not Empty")
    @Schema(description = "CPF", example = "058.156.036-74")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX ou XXXXXXXXXXX")
    @Length(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    @NotNull(message = "Data de Nascimento is Not Null")
    @Schema(description = "Data de Nascimento", example = "12/05/1986")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Senha Online is Not Null")
    @NotEmpty(message = "Senha OnLine  is Not Empty")
    @Schema(description = "Senha", example = "3525@85#ty9")
    private String senhaOnline;

    public void setnome(String nome) {
        this.nome = nome.toUpperCase();
    }

}

