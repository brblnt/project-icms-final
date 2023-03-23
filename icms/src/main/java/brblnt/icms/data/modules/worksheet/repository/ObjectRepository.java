package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.worksheet.model.ObjectJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Object Repository interface.
 */
public interface ObjectRepository extends JpaRepository<ObjectJPA, Long> {
}
