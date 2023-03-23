package brblnt.icms.data.modules.common.model.additional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

/**
 * City JPA class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "city_table")
@Data
public class CityJPA {

  @Id
  @Column(name = "postal_code", nullable = false, unique = true)
  private String postalCode;

  @Column(name = "city_name", nullable = false)
  private String cityName;

}
