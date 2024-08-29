package social.media.network.config.custorm_anonation.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import social.media.network.config.custorm_anonation.config.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {
    private String password;

    private String confirmPassword;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        var confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);
        return (passwordValue != null) ? passwordValue.equals(confirmPasswordValue) : confirmPasswordValue == null;
    }
}
