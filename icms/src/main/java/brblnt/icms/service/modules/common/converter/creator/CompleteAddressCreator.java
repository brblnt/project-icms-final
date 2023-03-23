package brblnt.icms.service.modules.common.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.common.model.additional.AddressJPA;
import brblnt.icms.data.modules.common.model.additional.CityJPA;
import brblnt.icms.data.modules.common.model.additional.FinanceJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.common.exceptions.NotFoundException;
import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;
import brblnt.icms.service.modules.common.repository.utility.additional.AddressRepositoryUtility;
import brblnt.icms.service.modules.common.repository.utility.additional.CityRepositoryUtility;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Complete Address Creator.
 * Create Complete Address from AddressJPA and City JPA.
 */
@Service
@RequiredArgsConstructor
public class CompleteAddressCreator implements CompleteCreator<CompleteAddress> {

  private final AddressRepositoryUtility addressRepositoryUtility;
  private final CityRepositoryUtility cityRepositoryUtility;

  @Override
  public List<CompleteAddress> getAll() {
    List<CompleteAddress> list = new ArrayList<>();
    List<AddressJPA> addresses = addressRepositoryUtility.getAll();
    for (AddressJPA address : addresses) {
      list.add(createById(address.getId()));
    }
    return list;
  }

  @Override
  public CompleteAddress createById(Long addressID) {

    CompleteAddress ca = new CompleteAddress();

    if (addressID == 0) {
      return createEmpty();
    }

    AddressJPA address;
    CityJPA city;

    try {
      address = addressRepositoryUtility.getById(addressID);
    } catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
    ca.setAddressID(address.getId());
    ca.setPostalCode(address.getPostalCode());
    try {
      city = cityRepositoryUtility.getById(address.getPostalCode());
    } catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
    ca.setCityName(city.getCityName());
    ca.setAddress(address.getAddress());
    ca.setOther(address.getOther());

    return ca;
  }

  /**
   * new Empty Address.
   */
  public CompleteAddress createEmpty() {
    CompleteAddress ca = new CompleteAddress();
    ca.setAddressID(0L);
    ca.setPostalCode("");
    ca.setCityName("");
    ca.setAddress("");
    ca.setOther("");
    return ca;
  }
}
