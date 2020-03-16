package alura.sportpot.domain.use_cases;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

import org.springframework.stereotype.Service;

/**
 * HashValueUseCase
 */
@Service
public class HashValueUseCase {

  public String execute(String value) {
    String hashedPassword = Hashing.sha256()
      .hashString(value, StandardCharsets.UTF_8)
      .toString();

    return hashedPassword;
  }
}