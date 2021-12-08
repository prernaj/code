package low.level.truecaller.model.common;

public class Contact {
    private String countryCode;
    private String phone;
    private String email;
    public Contact(String phone) {
        this.phone = phone;
    }
    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
    public Contact(String countryCode, String phone, String email) {
        this.countryCode = countryCode;
        this.phone = phone;
        this.email = email;
    }
}
