package brblnt.icms.data.modules.common.repository;

import brblnt.icms.data.modules.common.model.ColleagueJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ColleagueRepository interface.
 */
public interface ColleagueRepository extends JpaRepository<ColleagueJPA, Long> {
}

