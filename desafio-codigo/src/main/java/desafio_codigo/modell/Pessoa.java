package desafio_codigo.modell;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
}
