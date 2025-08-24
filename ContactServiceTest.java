import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    public void setup() {
        service = new ContactService();
        contact = new Contact("001", "Alice", "Smith", "1234567890", "456 Elm St");
        service.addContact(contact);
    }

    @Test
    public void testAddContactSuccess() {
        Contact newContact = new Contact("002", "Bob", "Jones", "0987654321", "789 Oak Ave");
        service.addContact(newContact);
        assertEquals("Bob", service.getContact("002").getFirstName());
    }

    @Test
    public void testAddContactDuplicateId() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact));
    }

    @Test
    public void testDeleteContactSuccess() {
        service.deleteContact("001");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("001"));
    }

    @Test
    public void testUpdateFields() {
        service.updateFirstName("001", "Alex");
        service.updateLastName("001", "Brown");
        service.updatePhone("001", "1112223333");
        service.updateAddress("001", "New Address");

        Contact updated = service.getContact("001");
        assertEquals("Alex", updated.getFirstName());
        assertEquals("Brown", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("New Address", updated.getAddress());
    }
        @Test
    public void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("ZZZ"));
    }

    @Test
    public void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("ZZZ", "Name"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("ZZZ", "Surname"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("ZZZ", "0123456789"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("ZZZ", "123 Road"));
    }
}
