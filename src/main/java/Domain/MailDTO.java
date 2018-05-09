package Domain;

public class MailDTO {

    private String to;
    private String from;
    private String subject;
    private String text;
    private String asHtml;

    public MailDTO(String to, String from, String subject, String text, String asHtml) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.text = text;
        this.asHtml = asHtml;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAsHtml() {
        return asHtml;
    }

    public void setAsHtml(String asHtml) {
        this.asHtml = asHtml;
    }
}
