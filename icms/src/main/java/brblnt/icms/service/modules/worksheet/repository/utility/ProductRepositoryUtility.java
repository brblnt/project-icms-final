package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.ProductJPA;
import brblnt.icms.data.modules.worksheet.repository.ProductRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ProductRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class ProductRepositoryUtility implements UtilityInterface<ProductJPA, ProductNotFoundException> {

  private final ProductRepository productRepository;

  @Override
  public List<ProductJPA> getAll() {
    return productRepository.findAll().stream().toList();
  }

  @Override
  public ProductJPA getById(Long id) throws ProductNotFoundException {
    for (ProductJPA product : getAll()) {
      if (Objects.equals(product.getId(), id)) {
        return product;
      }
    }
    throw new ProductNotFoundException("No product with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public ProductJPA getById(String id) throws ProductNotFoundException {
    return null;
  }

  @Override
  public void save(ProductJPA product) {
    productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
    productRepository.deleteById(id);
  }
}
