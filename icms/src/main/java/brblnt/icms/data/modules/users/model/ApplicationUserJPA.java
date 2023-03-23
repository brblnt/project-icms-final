package brblnt.icms.data.modules.users.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Represents a user in the database.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users_table")
@Data
public class ApplicationUserJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "email", nullable = false)
  private String emailAddress;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role", nullable = false)
  private String role;

  @Column(name = "password_changed", nullable = false)
  private boolean passwordChanged;

  @Column(name = "registration_date", nullable = false)
  private String registrationDate;

  @Column(name = "account_non_expired", nullable = false)
  private boolean accountNonExpired;

  @Column(name = "account_non_locked", nullable = false)
  private boolean accountNonLocked;

  @Column(name = "account_credentials_non_expired", nullable = false)
  private boolean accountCredentialsNonExpired;

  @Column(name = "account_enabled", nullable = false)
  private boolean accountEnabled;


}
