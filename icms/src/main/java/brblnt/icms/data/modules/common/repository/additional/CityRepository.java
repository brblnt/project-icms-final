package brblnt.icms.data.modules.common.repository.additional;

import brblnt.icms.data.modules.common.model.additional.CityJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CityRepository interface.
 */
public interface CityRepository extends JpaRepository<CityJPA, Long> {
}
