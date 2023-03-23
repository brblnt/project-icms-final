package brblnt.icms.service.modules.common.repository.utility.additional;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.common.model.additional.AddressJPA;
import brblnt.icms.data.modules.common.repository.additional.AddressRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Address Repository Utility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class AddressRepositoryUtility implements UtilityInterface<AddressJPA, NotFoundException> {

  private final AddressRepository addressRepository;

  @Override
  public List<AddressJPA> getAll() {
    return addressRepository.findAll().stream().toList();
  }

  @Override
  public AddressJPA getById(Long id) throws NotFoundException {
    for (AddressJPA address : getAll()) {
      if (Objects.equals(address.getId(), id)) {
        return address;
      }
    }
    throw new NotFoundException("No address with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public AddressJPA getById(String id) throws NotFoundException {
    return null;
  }

  @Override
  public void save(AddressJPA address) {
    addressRepository.save(address);
  }

  @Override
  public void delete(Long id) {

  }


}
