package equipe25.churninsight_backend.model.genero;

import equipe25.churninsight_backend.model.genero.enums.GeneroEnum;
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
@Table(name = "genero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GeneroEntidade {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String genero;

    public static GeneroEntidade fromEnum(GeneroEnum generoEnum) {
        return new GeneroEntidade(generoEnum.getId(), generoEnum.getDisplayName());
    }

}
