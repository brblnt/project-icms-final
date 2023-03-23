package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.common.model.additional.DealerJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dealer Repository interface.
 */
public interface DealerRepository extends JpaRepository<DealerJPA, Long> {
}
