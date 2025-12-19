package equipe25.churninsight_backend.model;

public class PredicaoChurnEntity {
<<<<<<< Updated upstream

    /*
     * Incluir as anotações acima no nome da classe, como entity, getter, setter,
     * allargsconstructor, noargsconstructor
     * table, equalsandhashcode
     * Incluir também todos os campos necessários para a entidade - olhar a DTO
     * DataPredictResposta - incluindo id, dataCriacao
     * Incluir todas as annotation necessárias nesta entidade
     */

=======

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

>>>>>>> Stashed changes
}
