package brblnt.icms.service.service.modules.worksheet;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.ProductJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteProductCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.modules.worksheet.repository.utility.ProductRepositoryUtility;
import brblnt.icms.web.model.modules.worksheet.product.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Product service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetProductService implements ServiceInterface<CompleteProduct, ProductRequest> {

  private final ModelMapper modelMapper;
  private final ProductRepositoryUtility productRepositoryUtility;
  private final CompleteProductCreator completeProductCreator;
  private final WorksheetDealerService worksheetDealerService;

  /**
   * Get all product from complete creator.
   */
  @Override
  public List<CompleteProduct> getAll() {
    return completeProductCreator.getAll();
  }

  /**
   * Get product by id from complete creator.
   */
  @Override
  public CompleteProduct getById(Long id) {
    return completeProductCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Check product custom code is unique.
   */
  public boolean exist(String id, String actualOwn) {
    if (actualOwn.toLowerCase().equals(id.toLowerCase())) {
      return false;
    }
    for (CompleteProduct actual : getAll()) {
      if (actual.getCustomCode().toLowerCase().equals(id.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Save product.
   */
  @Override
  public boolean save(ProductRequest productRequest, CompleteProduct completeProduct) {
    try {
      CompleteProduct save = create(productRequest);
      if (productRequest.getDealer() != 0) {
        save.setDealer(worksheetDealerService.getById((long) productRequest.getDealer()));
      }
      if (completeProduct.getId() != 0) {
        save.setId(completeProduct.getId());
      }
      productRepositoryUtility.save(modelMapper.map(save, ProductJPA.class));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete product.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      productRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map product request to complete product.
   */
  @Override
  public CompleteProduct create(ProductRequest productRequest) {
    return modelMapper.map(productRequest, CompleteProduct.class);
  }

  /**
   * Create empty product.
   */
  @Override
  public CompleteProduct createEmpty() {
    return new CompleteProduct(
            0L,
            "",
            "",
            0,
            0,
            0,
            0,
            0,
            0,
            worksheetDealerService.createEmpty(),
            ""
    );
  }

  /**
   * Get dealers without product own dealer.
   */
  public List<CompleteDealer> getDealerListWithoutOwner(Long id) {
    List<CompleteDealer> tempList = worksheetDealerService.getAll();
    tempList.removeIf(actual -> Objects.equals(actual.getId(), id));
    return tempList;
  }

  /**
   * Clear usage at dealers if product removed.
   */
  public void clearUsage(Long id) {
    for (CompleteProduct actual : getAll()) {
      if (Objects.equals(actual.getDealer().getId(), id)) {
        ProductJPA save = modelMapper.map(actual, ProductJPA.class);
        save.setDealerID(0);
        productRepositoryUtility.save(save);
      }
    }
  }
}
