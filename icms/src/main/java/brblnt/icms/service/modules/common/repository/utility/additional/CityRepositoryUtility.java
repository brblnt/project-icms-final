package brblnt.icms.service.modules.common.repository.utility.additional;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.common.model.additional.CityJPA;
import brblnt.icms.data.modules.common.repository.additional.CityRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * City Repository Utility.
 */
@Service
@RequiredArgsConstructor
public class CityRepositoryUtility implements UtilityInterface<CityJPA, NotFoundException> {

  private final CityRepository cityRepository;

  @Override
  public List<CityJPA> getAll() {
    return cityRepository.findAll().stream().toList();
  }

  /**
   * Not required.
   */
  @Override
  public CityJPA getById(Long id) throws NotFoundException {
    return null;
  }

  @Override
  public CityJPA getById(String id) throws NotFoundException {
    for (CityJPA city : getAll()) {
      if (Objects.equals(city.getPostalCode(), id)) {
        return city;
      }
    }
    throw new NotFoundException("No city with this postal code " + id);
  }

  @Override
  public void save(CityJPA city) {
    cityRepository.save(city);
  }

  @Override
  public void delete(Long id) {

  }
}
