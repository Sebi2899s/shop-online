package ro.itschool.Final.project.service.email;

public interface EmailSender {
    void sendEmail(String toEmail, String subject, String body);
}