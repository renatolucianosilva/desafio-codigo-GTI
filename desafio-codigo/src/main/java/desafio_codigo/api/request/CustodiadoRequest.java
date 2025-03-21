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
public class CustodiadoRequest {

    @NotNull(message = "Nome is Not Null")
    @NotEmpty(message = "Nome  is Not Empty")
    @Schema(description = "Nome", example = "Paulo Henrique da Silva")
    private String nome;

    @NotNull(message = "CPF is Not Null")
    @NotEmpty(message = "CPF  is Not Empty")
    @Schema(description = "CPF", example = "015.036.114-68")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX ou XXXXXXXXXXX")
    @Length(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    @NotNull(message = "Data de Nascimento is Not Null")
    @Schema(description = "Data de Nascimento", example = "02/10/1979")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Numero Prontuário is Not Null")
    @NotEmpty(message = "Numero Prontuário is Not Empty")
    @Pattern(regexp = "^\\d+$", message = "Somente Numeros.")
    @Schema(description = "Numero de Prontuário", example = "123456")
    @Length(min = 4, max = 8, message = "Numero de Prontuário deve ter entre 4 e 8 caracteres")
    private String numeroProntuario;

    @Schema(description = "Vulgo", example = "")
    private String vulgo;

    @NotNull(message = "Unidade Penal is Not Null")
    @NotEmpty(message = "Unidade Penal is Not Empty")
    @Schema(description = "Descrição Unidade Penal", example = "PDF I")
    private String descricaoUnidadePenal;

    public void setnome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setDescricao(String descricao) {
        this.descricaoUnidadePenal = descricao.toUpperCase();
    }

}
