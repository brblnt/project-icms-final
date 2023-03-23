package brblnt.icms.web.converter.modules.worksheet.product;

import brblnt.icms.service.modules.worksheet.converter.creator.CompleteDealerCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.web.model.modules.worksheet.product.request.ProductRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Product Request to Complete Product.
 */
@Component
@RequiredArgsConstructor
public class ConvertProductRequestToCompleteProduct implements Converter<ProductRequest, CompleteProduct> {

  private final CompleteDealerCreator completeDealerCreator;

  @Override
  public CompleteProduct convert(@NonNull final ProductRequest source) {
    return new CompleteProduct(
            null,
            source.getProductName(),
            source.getCustomCode(),
            source.getPriceN(),
            source.getPriceB(),
            source.getVat(),
            source.getPriceSellN(),
            source.getPriceSellB(),
            source.getVatSell(),
            completeDealerCreator.createById((long) source.getDealer()),
            source.getOther()
    );
  }
}
