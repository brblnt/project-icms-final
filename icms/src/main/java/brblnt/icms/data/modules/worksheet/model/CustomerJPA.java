package brblnt.icms.data.modules.worksheet.model;

import javax.persistence.*;

import lombok.*;

/**
 * Customers JPA model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "customers_table")
@Data
public class CustomerJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false)
  private String customerName;

  @Column(name = "address_ID", nullable = false)
  private int addressID;

  @Column(name = "address_ID2")
  private int addressID2;

  @Column(name = "address_ID3")
  private int addressID3;

  @Column(name = "finance_ID")
  private int financeID;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "email", nullable = false)
  private String emailAddress;

  @Column(name = "other", nullable = false)
  private String other;

  @Column(name = "registration_date", nullable = false)
  private String registrationDate;



}
