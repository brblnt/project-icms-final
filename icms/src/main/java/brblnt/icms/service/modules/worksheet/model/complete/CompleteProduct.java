package brblnt.icms.service.modules.worksheet.model.complete;

import lombok.*;

/**
 * Product service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteProduct {

  private Long id;

  private String productName;

  private String customCode;

  private double priceN;

  private double priceB;

  private int vat;

  private double priceSellN;

  private double priceSellB;

  private int vatSell;

  private CompleteDealer dealer;

  private String other;
}
