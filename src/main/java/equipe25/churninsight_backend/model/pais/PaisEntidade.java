package equipe25.churninsight_backend.model.pais;

import equipe25.churninsight_backend.model.pais.enums.PaisEnum;
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
@Table(name = "pais")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PaisEntidade {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String pais;

    public static PaisEntidade fromEnum(PaisEnum paisEnum) {
        return new PaisEntidade(paisEnum.getId(), paisEnum.name());
    }

}
