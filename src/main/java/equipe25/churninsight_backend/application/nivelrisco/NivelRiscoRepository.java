package equipe25.churninsight_backend.application.nivelrisco;

import org.springframework.data.jpa.repository.JpaRepository;

import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;

public interface NivelRiscoRepository extends JpaRepository<NivelRiscoEntidade, Integer> {

}
