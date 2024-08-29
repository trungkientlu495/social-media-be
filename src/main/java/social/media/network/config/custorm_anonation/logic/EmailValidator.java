package social.media.network.config.custorm_anonation.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import social.media.network.config.custorm_anonation.config.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        /*
        1  ^[a-zA-Z0-9._%+-]+ <--> local part cua email co the chua cac ki tu chu cai
            (in hoa va in thuong) , so va 1 so ky tu dac biet nhu dau cham gach duoi phan
            tram dau cong dau tru
        2  @ <--> ky tu @ phan tach giua local part va domain part
        3  [a-zA-Z0-9.-]+ <--> domain part cua email co the chua chu cai (in hoa va in thuong
            so va dau cham , dau gach ngang
        4  \\. <--> dau cham thuc su can khop
        5  [a-zA-Z]{2,} <--> phan mo rong ten mien co it nhat 2 ki tu
         */
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return value.matches(regexEmail);
    }
}
