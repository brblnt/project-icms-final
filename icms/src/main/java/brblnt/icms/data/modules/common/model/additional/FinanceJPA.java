package brblnt.icms.data.modules.common.model.additional;

import javax.persistence.*;

import lombok.*;

/**
 * Finance JPA class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "finance_table")
@Data
public class FinanceJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "bank_account_number", nullable = false)
  private String bankAccountNumber;

  @Column(name = "bank_account_number_international")
  private String bankAccountNumberInternational;

  @Column(name = "VAT_number", nullable = false)
  private String numberVAT;

  @Column(name = "eu_VAT_number", nullable = false)
  private String euNumberVAT;

}
