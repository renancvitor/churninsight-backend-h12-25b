package equipe25.churninsight_backend.infra.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
@Profile("!test")
@Slf4j
public class JpaWarmup {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Warm-up do JPA/Hibernate.
     *
     * Objetivos:
     * - Inicializar EntityManagerFactory
     * - Abrir conexão com o banco
     * - Evitar custo pesado no primeiro request do cliente
     * - Reduzir tempo percebido de startup na Render
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void warmup() {
        long start = System.currentTimeMillis();

        try {
            entityManager
                    .createNativeQuery("select 1")
                    .getSingleResult();

            long duration = System.currentTimeMillis() - start;
            log.info("JPA warm-up concluído em {} ms", duration);

        } catch (Exception e) {
            // Falhar cedo é melhor que subir quebrado
            log.error("Falha no warm-up do JPA. Aplicação será encerrada.", e);
            throw e;
        }
    }
}
