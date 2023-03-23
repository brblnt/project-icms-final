package brblnt.icms.service.interfaces;

import java.util.List;

/**
 * ServiceInterface.
 *
 * @param <E> CompleteObject.
 * @param <R> CorrectRequest for this CompleteObject.
 */
public interface ServiceInterface<E, R> {

  List<E> getAll();

  E getById(Long id);

  boolean exist(Long id);

  boolean save(R r, E o);

  boolean deleteById(Long id);

  E create(R r);

  E createEmpty();

}
