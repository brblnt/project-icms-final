package brblnt.icms.web.converter.modules.worksheet.product;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.web.model.modules.worksheet.product.response.ProductResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Product to Product Response.
 */
@Component
public class ConvertCompleteProductToProductResponse implements Converter<CompleteProduct, ProductResponse> {
  @Override
  public ProductResponse convert(@NonNull final CompleteProduct source) {
    return new ProductResponse(
            source.getId(),
            source.getProductName(),
            source.getCustomCode(),
            source.getPriceN(),
            source.getPriceB(),
            source.getVat(),
            source.getPriceSellN(),
            source.getPriceSellB(),
            source.getVatSell(),
            source.getDealer(),
            source.getOther()
    );
  }
}
