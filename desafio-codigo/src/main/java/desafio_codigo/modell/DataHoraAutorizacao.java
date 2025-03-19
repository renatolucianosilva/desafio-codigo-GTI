package desafio_codigo.modell;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "data_hora_autorizacao")
public class DataHoraAutorizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutorizacao;

    @Column(name="data_hora_autorizado")

    private LocalDateTime dataHoraAutorizado;
}
