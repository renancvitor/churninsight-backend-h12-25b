package equipe25.churninsight_backend.model.pais;

import equipe25.churninsight_backend.model.pais.enums.PaisEnum;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "pais")
@Getter
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PaisEnum paisEnum;

    @Column(nullable = false)
    private String nome;

    protected Pais() {
    }

    public Pais(PaisEnum paisEnum) {
        this.paisEnum = paisEnum;
        this.nome = paisEnum.getNome();
    }


}
