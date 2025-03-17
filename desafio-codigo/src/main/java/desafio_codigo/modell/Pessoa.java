package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pessoa")
    private int idPessoa;
    @Column(name="nome")
    private String nome;
    @Column(name="CPF")
    private String cpf;
    @Column(name="data_nascimento")
    private LocalDate dataNascimento;

}
