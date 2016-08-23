package vladimirov.alexander.registration_system.domain_layer;

/**
 * Created by zumba on 29.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Contact {
    public final Integer id;
    public final User user;
    public final String cellPhone;
    public final String email;
    public final Address address;

    public Contact(Integer id, User user, String cellPhone, String email, Address address) {
        this.id = id;
        this.user = user;
        this.cellPhone = cellPhone;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (user != null ? !user.equals(contact.user) : contact.user != null) return false;
        if (cellPhone != null ? !cellPhone.equals(contact.cellPhone) : contact.cellPhone != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return address != null ? address.equals(contact.address) : contact.address == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (cellPhone != null ? cellPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
