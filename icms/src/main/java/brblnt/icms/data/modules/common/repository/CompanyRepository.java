package brblnt.icms.data.modules.common.repository;

import brblnt.icms.data.modules.common.model.CompanyJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CompanyRepository interface.
 */
public interface CompanyRepository extends JpaRepository<CompanyJPA, Long> {
}
