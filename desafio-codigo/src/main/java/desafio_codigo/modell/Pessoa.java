package desafio_codigo.modell;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pessoa")
    private Long idPessoa;

    @Column(name="nome")
    private String nome;

    @Column(name="CPF", unique = true)
    @Length(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX ou XXXXXXXXXXX")
    private String cpf;

    @Column(name="data_nascimento")
    private LocalDate dataNascimento;

}
