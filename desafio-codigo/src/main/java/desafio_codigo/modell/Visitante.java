package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "visitante")
@PrimaryKeyJoinColumn(name = "id_visitante")
public class Visitante extends Pessoa{

    @Column(name="senha_online")
    private String senhaOnline;

    @OneToMany(mappedBy = "visitante")
    private List<AgendamentoVisita> agendamentos;


}
