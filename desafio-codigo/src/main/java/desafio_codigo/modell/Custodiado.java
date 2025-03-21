package desafio_codigo.modell;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "custodiado")
@PrimaryKeyJoinColumn(name = "id_custodiado")
public class Custodiado extends Pessoa{

    @Column(name = "numero_protuario")
    @Pattern(regexp = "^\\d+$", message = "Somente Numeros.")
    @Schema(description = "Numero de Prontuário", example = "123456")
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
