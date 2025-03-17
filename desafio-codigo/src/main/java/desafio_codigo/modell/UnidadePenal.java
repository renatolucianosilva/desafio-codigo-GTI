package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "unidade_penal")
public class UnidadePenal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_unidade")
    private int idUnidade;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @OneToMany(mappedBy = "unidadePenal")
    private List<Custodiado> custodiados;
}
