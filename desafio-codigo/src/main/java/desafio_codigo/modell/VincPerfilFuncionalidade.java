package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="vinc_perfil_funcionalidade")
public class VincPerfilFuncionalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vinc_perfil_func")
    private Long idVincPerfilFunc;

    @ManyToOne
    @JoinColumn(name="id_perfil")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name="funcionalidade")
    private Funcionalidade funcionalidade;
}
