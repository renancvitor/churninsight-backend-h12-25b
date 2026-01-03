package equipe25.churninsight_backend.model.previsao;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String recomendacao;

    @ElementCollection
    @CollectionTable(name = "explicabilidade_previsao", joinColumns = @JoinColumn(name = "previsao_id"))
    @Column(name = "fator", nullable = false)
    private List<String> explicabilidade = new ArrayList<>();

    public void setExplicabilidade(List<String> explicabilidade) {
        this.explicabilidade = explicabilidade == null
                ? new ArrayList<>()
                : explicabilidade;
    }

}
