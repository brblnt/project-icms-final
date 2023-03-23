package brblnt.icms.data.modules.worksheet.model;

import javax.persistence.*;

import lombok.*;

/**
 * Object JPA model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "objects_table")
@Data
public class ObjectJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "customerID", nullable = false)
  private int customerID;

  @Column(name = "item_name", nullable = false)
  private String itemName;

  @Column(name = "item_brand", nullable = false)
  private String itemBrand;

  @Column(name = "item_type", nullable = false)
  private String itemType;

  @Column(name = "item_serial", nullable = false)
  private String itemSerial;

  @Column(name = "other", nullable = false)
  private String other;


}
