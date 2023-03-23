package brblnt.icms.data.modules.common.repository.additional;

import brblnt.icms.data.modules.common.model.additional.AddressJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AddressRepository interface.
 */
public interface AddressRepository extends JpaRepository<AddressJPA, Long> {
}
