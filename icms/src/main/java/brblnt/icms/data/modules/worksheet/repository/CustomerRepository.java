package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.worksheet.model.CustomerJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Customer Repository interface.
 */
public interface CustomerRepository extends JpaRepository<CustomerJPA, Long> {
}
