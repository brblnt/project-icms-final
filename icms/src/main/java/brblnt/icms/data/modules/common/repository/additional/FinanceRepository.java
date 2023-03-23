package brblnt.icms.data.modules.common.repository.additional;

import brblnt.icms.data.modules.common.model.additional.FinanceJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FinanceRepository interface.
 */
public interface FinanceRepository extends JpaRepository<FinanceJPA, Long> {
}
