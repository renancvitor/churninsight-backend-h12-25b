package equipe25.churninsight_backend.model;

import equipe25.churninsight_backend.enuns.PrevisaoChurnEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nivel_risco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PrevisaoChrunEntidade {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String previsaoNome;

    public static PrevisaoChrunEntidade fromEnum(PrevisaoChurnEnum previsaoChurnEnum) {
        return new PrevisaoChrunEntidade(previsaoChurnEnum.getId(), previsaoChurnEnum.name());
    }

}
