package brblnt.icms;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Entrypoint of the application.
 */
@SpringBootApplication
public class IcmsApplication {


  private static ConfigurableApplicationContext context;

  /**
   * Main method start everything in the application.
   *
   * @param args cli attributes.
   */
  public static void main(String[] args) {
    context = SpringApplication.run(IcmsApplication.class, args);
  }

  /**
   * Restart the application.
   */
  public static void restart() {
    ApplicationArguments args = context.getBean(ApplicationArguments.class);

    Thread thread = new Thread(() -> {
      context.close();
      context = SpringApplication.run(IcmsApplication.class, args.getSourceArgs());
    });

    thread.setDaemon(false);
    thread.start();
  }
}
