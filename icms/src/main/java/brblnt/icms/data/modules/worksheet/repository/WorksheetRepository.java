package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.worksheet.model.WorksheetJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Worksheet Repository interface.
 */
public interface WorksheetRepository  extends JpaRepository<WorksheetJPA, Long> {
}
