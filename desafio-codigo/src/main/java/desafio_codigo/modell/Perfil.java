package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_perfil")
    private int idPerfil;
    @Column(name="descricao")
    private String descrição;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "perfil")
    private List<VincPerfilFuncionalidade> vincFuncionalidades;

}
