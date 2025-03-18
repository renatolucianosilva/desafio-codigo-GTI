package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="funcionalidade")
public class Funcionalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_funcionalidade")
    private Long idFuncionalidade;
    @Column(name="descricao")
    private String descricao;
    @Column(name="autority")
    private String autority;
    @OneToMany(mappedBy = "funcionalidade")
    private List<VincPerfilFuncionalidade> vinculoPerfis;

}
