package social.media.network.config.custorm_anonation.config;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import social.media.network.config.custorm_anonation.logic.PasswordMatchesValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String password();

    String confirmPassword();

    String message() default "Enter password and confirm password do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
