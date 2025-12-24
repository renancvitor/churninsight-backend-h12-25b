package equipe25.churninsight_backend.model.previsao;

import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;
import equipe25.churninsight_backend.model.tipoprevisao.TipoPrevisaoEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "previsao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Previsao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_previsao_id", nullable = false)
    private TipoPrevisaoEntidade previsao;

    @ManyToOne
    @JoinColumn(name = "nivel_risco_id", nullable = false)
    private NivelRiscoEntidade nivelRisco;

    @Column(nullable = false)
    private Double probabilidade;

    // @Column(nullable = false)
    // private String recomendacao;

}
