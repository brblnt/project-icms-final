package brblnt.icms.data.modules.worksheet.repository;

import brblnt.icms.data.modules.worksheet.model.ProductJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product Repository interface.
 */
public interface ProductRepository extends JpaRepository<ProductJPA, Long> {
}
