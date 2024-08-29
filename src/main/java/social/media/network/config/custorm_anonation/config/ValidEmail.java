package social.media.network.config.custorm_anonation.config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import social.media.network.config.custorm_anonation.logic.EmailValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidEmail {
    String message() default "Email: Must be a valid email address.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
