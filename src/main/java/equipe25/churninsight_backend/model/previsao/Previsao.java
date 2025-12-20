package equipe25.churninsight_backend.model.previsao;

import equipe25.churninsight_backend.model.TipoPrevisao.TipoPrevisaoEntidade;
import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "predictions")
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
    @Column(name = "tipo_previsao", nullable = false)
    private TipoPrevisaoEntidade previsao;

    @ManyToOne
    @Column(name = "nivel_risco", nullable = false)
    private NivelRiscoEntidade nivelRisco;

    @Column(nullable = false)
    private Double probabilidade;

    @Column(nullable = false)
    private String recomendacao;

}
