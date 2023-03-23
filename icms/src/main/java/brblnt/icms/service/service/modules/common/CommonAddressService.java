package brblnt.icms.service.service.modules.common;

import java.util.List;

import brblnt.icms.data.modules.common.model.additional.AddressJPA;
import brblnt.icms.data.modules.common.model.additional.CityJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.repository.utility.additional.AddressRepositoryUtility;
import brblnt.icms.service.modules.common.repository.utility.additional.CityRepositoryUtility;
import brblnt.icms.web.model.modules.common.request.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Address service.
 */
@Service
@RequiredArgsConstructor
public class CommonAddressService implements ServiceInterface<CompleteAddress, AddressRequest> {

  private final ModelMapper modelMapper;
  private final AddressRepositoryUtility addressRepositoryUtility;
  private final CityRepositoryUtility cityRepositoryUtility;
  private final CompleteAddressCreator completeAddressCreator;

  /**
   * Get all address from complete creator.
   */
  @Override
  public List<CompleteAddress> getAll() {
    return completeAddressCreator.getAll();
  }

  /**
   * Get address by id from complete creator.
   */
  @Override
  public CompleteAddress getById(Long id) {
    return completeAddressCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Not required.
   */
  @Override
  public boolean save(AddressRequest request, CompleteAddress completeAddress) {
    try {
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Save Address with new data.
   */
  public int saveAddress(AddressRequest request, Long addressID) {
    AddressJPA tempAddress = new AddressJPA();
    tempAddress.setAddress(request.getAddress());
    tempAddress.setOther(request.getOther());
    tempAddress.setPostalCode(request.getPostalCode());
    if (addressID != 0) {
      tempAddress.setId(addressID);
    }
    addressRepositoryUtility.save(tempAddress);
    CityJPA tempCity = new CityJPA();
    tempCity.setPostalCode(request.getPostalCode());
    for (CityJPA city : cityRepositoryUtility.getAll()) {
      if (city.getPostalCode().equals(tempCity.getPostalCode())) {
        tempCity.setPostalCode(city.getPostalCode());
      }
    }
    tempCity.setCityName(request.getCityName());
    cityRepositoryUtility.save(tempCity);

    return Math.toIntExact(addressRepositoryUtility.getAll().get(addressRepositoryUtility.getAll().size() - 1).getId());
  }

  /**
   * Not required.
   */
  @Override
  public boolean deleteById(Long id) {
    return false;
  }

  /**
   * Map Address request to Complete Address.
   */
  @Override
  public CompleteAddress create(AddressRequest request) {
    return modelMapper.map(request, CompleteAddress.class);
  }

  /**
   * Not required.
   */
  @Override
  public CompleteAddress createEmpty() {
    return null;
  }

}
