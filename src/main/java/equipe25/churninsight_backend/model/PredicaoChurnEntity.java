package equipe25.churninsight_backend.model;

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
public class PredicaoChurnEntity {
    /*
     * Incluir as anotações acima no nome da classe, como entity, getter, setter,
     * allargsconstructor, noargsconstructor
     * table, equalsandhashcode
     * Incluir também todos os campos necessários para a entidade - olhar a DTO
     * DataPredictResposta - incluindo id, dataCriacao
     * Incluir todas as annotation necessárias nesta entidade
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrevisaoChurnEnum previsao;

    @Column(nullable = false)
    private Double probabilidade;

    @Column(nullable = false)
    private String recomendacao;


}
