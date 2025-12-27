package equipe25.churninsight_backend.model.nivelrisco;

import equipe25.churninsight_backend.model.nivelrisco.enums.NivelRiscoEnum;
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
public class NivelRiscoEntidade {

    @Id
    private Integer id;

    @Column(name = "nivel_risco", nullable = false, unique = true)
    private String nivelRiscoNome;

    public static NivelRiscoEntidade fromEnum(NivelRiscoEnum nivelRiscoEnum) {
        return new NivelRiscoEntidade(nivelRiscoEnum.getId(), nivelRiscoEnum.getDisplayName());
    }

}
