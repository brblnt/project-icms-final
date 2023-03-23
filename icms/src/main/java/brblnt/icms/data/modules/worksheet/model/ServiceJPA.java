package brblnt.icms.data.modules.worksheet.model;

import javax.persistence.*;

import lombok.*;

/**
 * Service JPA model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "services_table")
@Data
public class ServiceJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false)
  private String serviceName;

  @Column(name = "custom_ID", nullable = false)
  private String customCode;

  @Column(name = "price_n", nullable = false)
  private double priceN;

  @Column(name = "price_b", nullable = false)
  private double priceB;

  @Column(name = "vat", nullable = false)
  private int vat;

}
