package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Fault Repository interface.
 */
public interface FaultRepository extends JpaRepository<FaultJPA, Long> {
}
