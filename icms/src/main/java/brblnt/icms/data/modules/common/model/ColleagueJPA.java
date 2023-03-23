package brblnt.icms.data.modules.common.model;


import javax.persistence.*;

import lombok.*;

/**
 * Colleague JPA class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "colleague_table")
@Data
public class ColleagueJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "custom_ID", nullable = false)
  private String customCode;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "email", nullable = false)
  private String emailAddress;

  @Column(name = "position", nullable = false)
  private String position;

  @Column(name = "qualifications", nullable = false)
  private String qualifications;

  @Column(name = "working_area", nullable = false)
  private String workingArea;

  @Column(name = "other", nullable = false)
  private String other;

  @Column(name = "registration_date", nullable = false)
  private String registrationDate;
}
