package brblnt.icms.service.interfaces;


import java.util.List;

/**
 * CompleteCreator interface.
 *
 * @param <T> completeObject.
 */
public interface CompleteCreator<T> {

  List<T> getAll();

  T createById(Long id);
}
