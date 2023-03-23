package brblnt.icms.data.modules.worksheet.model;

import javax.persistence.*;

import lombok.*;

/**
 * Fault JPA model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "fault_table")
@Data
public class FaultJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "fault_name", nullable = false, unique = true)
  private String faultName;

  @Column(name = "fault_other")
  private String faultOther;

}
