package brblnt.icms.data.modules.worksheet.model;

import javax.persistence.*;

import lombok.*;

/**
 * Worksheet JPA model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "worksheets_table")
@Data
public class WorksheetJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "state")
  private String state;

  @Column(name = "engineer_code")
  private String engineerCode;

  @Column(name = "customer_ID")
  private int customerID;

  @Column(name = "object_ID")
  private int objectID;

  @Column(name = "faults_ID")
  private String faultsID;

  @Column(name = "services_ID")
  private String servicesID;

  @Column(name = "products_ID")
  private String productsID;

  @Column(name = "create_date")
  private String createDate;

  @Column(name = "finish_date")
  private String finishDate;

  @Column(name = "other")
  private String other;

}
