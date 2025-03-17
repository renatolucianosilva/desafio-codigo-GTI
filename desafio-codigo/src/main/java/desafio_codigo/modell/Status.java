package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatus;
    @Column(name="descricao")
    private String descricao;
    @OneToMany(mappedBy = "status")
    private List<AgendamentoVisita> agendamentos;
}
