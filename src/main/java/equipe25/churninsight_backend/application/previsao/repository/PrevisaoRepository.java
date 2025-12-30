package equipe25.churninsight_backend.application.previsao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.model.previsao.Previsao;

public interface PrevisaoRepository extends JpaRepository<Previsao, Long> {

    @Query("""
            SELECT new equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco(
            nr.nivelRiscoNome,
            COUNT(p.id)
            )
            FROM Previsao p
            JOIN p.nivelRisco nr
            GROUP BY nr.nivelRiscoNome
            ORDER BY nr.nivelRiscoNome
            """)
    List<PrevisaoPorNivelRisco> previsaoPorNivelRiscos();
}
