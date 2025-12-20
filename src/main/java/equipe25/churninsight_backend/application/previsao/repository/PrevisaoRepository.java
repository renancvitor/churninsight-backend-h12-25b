package equipe25.churninsight_backend.application.previsao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco;
import equipe25.churninsight_backend.model.previsao.Previsao;

public interface PrevisaoRepository extends JpaRepository<Previsao, Long> {

    @Query("""
            SELECT new equipe25.churninsight_backend.application.previsao.dto.PrevisaoPorNivelRisco(
            p.nivelRisco,
            COUNT(p)
            )
            FROM Previsao p
            GROUP BY p.nivelRisco
            """)
    List<PrevisaoPorNivelRisco> previsaoPorNivelRiscos();

}
