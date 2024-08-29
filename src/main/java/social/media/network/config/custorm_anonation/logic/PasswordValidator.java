package social.media.network.config.custorm_anonation.logic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import social.media.network.config.custorm_anonation.config.StrongPassword;

public class PasswordValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        Có độ dài tối thiểu là 8 ký tự. Điều chỉnh bằng cách sửa đổi {8,}
//        Ít nhất một chữ cái tiếng Anh viết hoa. Bạn có thể xóa điều kiện này bằng cách xóa (?=.*?[AZ])
//        Ít nhất một chữ cái tiếng Anh thường. Bạn có thể xóa điều kiện này bằng cách xóa (?=.*?[az])
//        Ít nhất một chữ số. Bạn có thể loại bỏ điều kiện này bằng cách loại bỏ (?=.*?[0-9])
//        Ít nhất một ký tự đặc biệt, Bạn có thể xóa điều kiện này bằng cách xóa (?=.*?[#?!@$%^&*-])
        String regexPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return value.matches(regexPassword);
    }
}
