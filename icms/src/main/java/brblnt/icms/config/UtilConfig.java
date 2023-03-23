package brblnt.icms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Utility Configuration.
 * Configure dependencies to Spring can find their beans.
 */
@Configuration
public class UtilConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
