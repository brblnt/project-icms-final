package brblnt.icms.web.converter.modules.common;

import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.web.model.modules.common.response.ColleagueResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Complete Colleague to Colleague Response.
 */
@Component
public class ConvertCompleteColleagueToColleagueResponse implements Converter<CompleteColleague, ColleagueResponse> {
  @Override
  public ColleagueResponse convert(CompleteColleague source) {
    return new ColleagueResponse(
            source.getId(),
            source.getCustomCode(),
            source.getName(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getPosition(),
            source.getQualifications(),
            source.getWorkingArea(),
            source.getOther(),
            source.getRegistrationDate()
    );
  }
}
