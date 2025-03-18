package desafio_codigo.modell;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "custodiado")
@PrimaryKeyJoinColumn(name = "id_custodiado")
public class Custodiado extends Pessoa{

    @Column(name = "numero_protuario")
    private String numeroProntuario;

    @Column(name = "vulgo")
    private String vulgo;

    @ManyToOne
    @JoinColumn(name="id_unidade_penal")
    private UnidadePenal unidadePenal;

    @OneToMany(mappedBy = "custodiado")
    private List<AgendamentoVisita> agendamentos;


    public Custodiado createCustodiado(UnidadePenal unidadePenal) {

        this.unidadePenal = unidadePenal;

        return this;
    }


}
