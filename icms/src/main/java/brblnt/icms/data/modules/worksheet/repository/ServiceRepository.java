package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Service Repository interface.
 */
public interface ServiceRepository extends JpaRepository<ServiceJPA, Long> {
}
