package brblnt.icms.data.modules.users;


import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA interface for user storage.
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserJPA, Long> {
}