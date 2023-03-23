package brblnt.icms.web.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Login success handler.
 */
@Component
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws ServletException, IOException {

    String redirectURL = request.getContextPath();

    String userRole = authentication
            .getAuthorities()
            .toArray()[authentication.getAuthorities().toArray().length - 1]
            .toString().replace("ROLE_", "");


    if (userRole.equals("ADMIN")) {
      log.info("Administrator redirect.");
      redirectURL = "/admin/home";
    } else if (userRole.equals("SUPER_USER")) {
      log.info("Super user redirect.");
      redirectURL = "/super-user/home";
    } else if (userRole.equals("SIMPLE_USER")) {
      log.info("Simple user redirect.");
      redirectURL = "/user/home";
    }

    response.sendRedirect(redirectURL);

  }

}
