package equipe25.churninsight_backend.model.TipoPrevisao;

import equipe25.churninsight_backend.model.TipoPrevisao.enums.TipoPrevisaoEnum;
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
@Table(name = "tipo_previsao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoPrevisaoEntidade {

    @Id
    private Integer id;

    @Column(name = "tipo_previsao", nullable = false, unique = true)
    private String tipoPrevisao;

    public static TipoPrevisaoEntidade fromEnum(TipoPrevisaoEnum previsaoChurnEnum) {
        return new TipoPrevisaoEntidade(previsaoChurnEnum.getId(), previsaoChurnEnum.name());
    }

}
