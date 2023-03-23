package brblnt.icms.web.model.modules.worksheet.product.request;

import lombok.*;

/**
 * Web layer model for product.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ProductRequest {

  private String productName;

  private String customCode;

  private double priceN;

  private double priceB;

  private int vat;

  private double priceSellN;

  private double priceSellB;

  private int vatSell;

  private int dealer;

  private String other;

}
