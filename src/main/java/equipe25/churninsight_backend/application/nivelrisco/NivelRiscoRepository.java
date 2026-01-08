package equipe25.churninsight_backend.application.nivelrisco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;

public interface NivelRiscoRepository extends JpaRepository<NivelRiscoEntidade, Integer> {

    Optional<NivelRiscoEntidade> findByNivelRiscoNome(String nivelRisco);

}
