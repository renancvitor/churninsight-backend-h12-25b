package equipe25.churninsight_backend.infra.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import equipe25.churninsight_backend.application.nivelrisco.NivelRiscoRepository;
import equipe25.churninsight_backend.application.tipoprevisao.TipoPrevisaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile("!test")
@Slf4j
@RequiredArgsConstructor
public class JpaWarmup {

    private final NivelRiscoRepository nivelRiscoRepository;
    private final TipoPrevisaoRepository tipoPrevisaoRepository;

    /**
     * Warm-up real do JPA/Hibernate.
     *
     * Objetivos:
     * - Abrir pool de conexões
     * - Preparar queries reais usadas no batch
     * - Evitar custo pesado na primeira requisição
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional(readOnly = true)
    public void warmup() {

        long start = System.currentTimeMillis();

        try {
            nivelRiscoRepository.findAll();
            tipoPrevisaoRepository.findAll();

            long duration = System.currentTimeMillis() - start;
            log.info("JPA warm-up REAL concluído em {} ms", duration);

        } catch (Exception e) {
            log.error("Falha no warm-up do JPA. Aplicação será encerrada.", e);
            throw e;
        }
    }

}
