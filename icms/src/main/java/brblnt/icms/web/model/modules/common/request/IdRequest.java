package brblnt.icms.web.model.modules.common.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Address update additional request.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class IdRequest {
  private Long id;
  private int position;
}
