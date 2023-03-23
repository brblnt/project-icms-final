package brblnt.icms.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Password Encoder.
 */
@Component
@RequiredArgsConstructor
public class PasswordGenerator {

  private final PasswordEncoder passwordEncoder;

  private boolean generateTrueFalse() {
    if ((int) ((Math.random() * 2) + 1) == 1) {
      return true;
    } else {
      return false;
    }
  }

  private char generateChar() {
    char temp = 'a';
    for (int i = 0; i < (int) ((Math.random() * 24) + 1); i++) {
      temp++;
    }
    return temp;
  }

  private int generateNum() {
    int temp = 1;
    for (int i = 0; i < (int) ((Math.random() * 9) + 1); i++) {
      temp++;
    }
    return temp;
  }

  private String generatePass(int length) {
    String pass = "";
    String[] assy = new String[length];
    for (int i = 0; i < length; i++) {
      if (generateTrueFalse()) {
        if (generateTrueFalse()) {

          assy[i] = (generateChar() + "").toUpperCase();
        } else {
          assy[i]  = (generateChar() + "");
        }
      } else {
        assy[i] = ("" + generateNum());
      }

    }
    for (int i = 0; i < length; i++) {
      pass += assy[i];
    }
    return pass;
  }

  /**
   * Generate Password for create user.
   *
   * @return an array 0. index is raw password 1. index is encoded.
   */
  public String[] generatePassword() {
    String temp = generatePass(12);
    String[] array = {temp, passwordEncoder.encode(temp), };
    return array;
  }

  public String encode(String raw) {
    return passwordEncoder.encode(raw);
  }
}

