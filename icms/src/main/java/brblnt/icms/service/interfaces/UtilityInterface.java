package brblnt.icms.service.interfaces;

import java.util.List;

/**
 * UtilityInterface for repository utilities.
 *
 * @param <T> JPA object.
 * @param <E> @{JPA}NotFoundException.
 */
public interface UtilityInterface<T, E extends Exception> {

  List<T> getAll();

  T getById(Long id) throws E;

  T getById(String id) throws E;

  void save(T o);

  void delete(Long id);
}
