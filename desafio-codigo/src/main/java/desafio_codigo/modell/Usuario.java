package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Usuario extends Pessoa{

    @Column(name="email")
    private String email;

    @Column(name="senha")
    private String senha;

    @ManyToOne
    @JoinColumn(name="id_perfil")
    private Perfil perfil;


}
