package brblnt.icms.data.modules.worksheet.model;

import javax.persistence.*;

import lombok.*;

/**
 * Product JPA model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "products_table")
@Data
public class ProductJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "custom_ID", nullable = false)
  private String customCode;

  @Column(name = "price_n", nullable = false)
  private double priceN;

  @Column(name = "price_b", nullable = false)
  private double priceB;

  @Column(name = "vat", nullable = false)
  private int vat;

  @Column(name = "price_sell_n", nullable = false)
  private double priceSellN;

  @Column(name = "price_sell_b", nullable = false)
  private double priceSellB;

  @Column(name = "vat_sell", nullable = false)
  private int vatSell;

  @Column(name = "dealer_ID", nullable = false)
  private int dealerID;

  @Column(name = "other", nullable = false)
  private String other;

}
