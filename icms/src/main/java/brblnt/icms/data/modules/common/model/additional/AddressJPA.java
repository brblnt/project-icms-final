package brblnt.icms.data.modules.common.model.additional;

import javax.persistence.*;

import lombok.*;

/**
 * Address JPA class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "address_table")
@Data
public class AddressJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "other", nullable = false)
  private String other;


}
