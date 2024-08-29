package social.media.network.services;


public interface EmailServices {
    void sendEmailRegister(String to, String subject, String text);

    void sendEmailForgot(String to, String subject, String text);
}
