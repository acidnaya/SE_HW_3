package restaurant.agents;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс для доступа ко всем агентам
 */
@UtilityClass
@Slf4j
public class AgentRepository {
    @Getter
    private static final List<Agent<?>> AGENTS = new CopyOnWriteArrayList<>();

    /**
     * регистрирует нового агента, чтобы другие могли его найти
     *
     * @param agent регистрируемый агент
     */
    public static synchronized void register(Agent<?> agent) {
        if (!AGENTS.contains(agent)) {
            AGENTS.add(agent);
        }

        log.debug("Agent registered: {}", agent.getName());
    }

    /**
     * удаляет агента, когда его работа завершена
     *
     * @param agent удаляемый агент
     */
    public static synchronized void remove(Agent<?> agent) {
        AGENTS.remove(agent);

        log.debug("Agent removed from repository: {}", agent.getName());
    }

    public static synchronized void stopAll() {
        for (var agent : AGENTS) {
            Agent.stop(agent);
            log.debug("Agent removed from repository: {}", agent.getName());
        }
    }
}