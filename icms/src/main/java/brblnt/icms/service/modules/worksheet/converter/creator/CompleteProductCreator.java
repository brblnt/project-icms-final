package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.ProductJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.worksheet.exceptions.ProductNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.modules.worksheet.repository.utility.ProductRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create Complete Product.
 * Convert JPA to CompleteObject.
 */
@Service
@RequiredArgsConstructor
public class CompleteProductCreator implements CompleteCreator<CompleteProduct> {

  private final ProductRepositoryUtility productRepositoryUtility;
  private final CompleteDealerCreator completeDealerCreator;

  @Override
  public List<CompleteProduct> getAll() {
    List<CompleteProduct> list = new ArrayList<>();
    List<ProductJPA> products = productRepositoryUtility.getAll();
    for (ProductJPA product : products) {
      list.add(createById(product.getId()));
    }
    return list;
  }

  @Override
  public CompleteProduct createById(Long productID) {
    CompleteProduct cp = new CompleteProduct();

    ProductJPA tempProduct;
    try {
      tempProduct = productRepositoryUtility.getById(productID);
    } catch (ProductNotFoundException e) {
      throw new RuntimeException(e);
    }

    cp.setId(tempProduct.getId());
    cp.setProductName(tempProduct.getProductName());
    cp.setCustomCode(tempProduct.getCustomCode());
    cp.setPriceN(tempProduct.getPriceN());
    cp.setPriceB(tempProduct.getPriceB());
    cp.setVat(tempProduct.getVat());
    cp.setPriceSellN(tempProduct.getPriceSellN());
    cp.setPriceSellB(tempProduct.getPriceSellB());
    cp.setVatSell(tempProduct.getVatSell());
    cp.setDealer(completeDealerCreator.createById((long) tempProduct.getDealerID()));
    cp.setOther(tempProduct.getOther());
    return cp;
  }
}
