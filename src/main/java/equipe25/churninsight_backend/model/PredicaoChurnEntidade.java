package equipe25.churninsight_backend.model;

import equipe25.churninsight_backend.enuns.NivelRiscoEnum;
import equipe25.churninsight_backend.enuns.PrevisaoChurnEnum;
import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;
    // private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrevisaoChurnEnum previsao;
    // private PrevisaoChrunEntidade previsao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelRiscoEnum nivelRisco;
    // private NivelRiscoEntidade nivelRisco;

    @Column(nullable = false)
    private Double probabilidade;

    @Column(nullable = false)
    private String recomendacao;

}
