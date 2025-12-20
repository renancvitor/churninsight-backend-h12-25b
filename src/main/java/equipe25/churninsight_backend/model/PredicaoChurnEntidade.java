package equipe25.churninsight_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PredicaoChurnEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private PrevisaoChrunEntidade previsao;

    @Column(name = "nivel_risco", nullable = false)
    private NivelRiscoEntidade nivelRisco;

    @Column(nullable = false)
    private Double probabilidade;

    @Column(nullable = false)
    private String recomendacao;

}
