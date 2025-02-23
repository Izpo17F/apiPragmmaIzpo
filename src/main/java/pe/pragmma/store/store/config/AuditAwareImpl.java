package pe.pragmma.store.store.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication autentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(autentication)) {
            return Optional.of(autentication.getName());
        } else {
            return Optional.of("Non Auth audit");

        }
    }
}
