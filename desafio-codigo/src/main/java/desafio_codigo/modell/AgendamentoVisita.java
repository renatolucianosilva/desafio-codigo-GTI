package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name="agendamento_visita")
public class AgendamentoVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long idAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_custodiado")
    private Custodiado custodiado;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Visitante visitante;

    @Column(name = "data_hora_agendamento")
    private LocalDateTime dataHoraAgendamento;

    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;

}
