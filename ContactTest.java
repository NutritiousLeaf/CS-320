import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void testValidContactCreation() {
        Contact c = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    public void testInvalidContactId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testInvalidPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "12345", "123 Main St");
        });
    }

    @Test
    public void testSettersValidation() {
        Contact c = new Contact("123", "John", "Doe", "1234567890", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
        @Test
    public void testBoundaryLengthsAccepted() {
        // 10-char first and last, 30-char address, 10-digit phone accepted
        String tenChars = "ABCDEFGHIJ"; // 10 chars
        String thirtyChars = "123456789012345678901234567890"; // 30 chars
        Contact c = new Contact("1234567890", tenChars, tenChars, "0123456789", thirtyChars);
        assertEquals(tenChars, c.getFirstName());
        assertEquals(tenChars, c.getLastName());
        assertEquals("0123456789", c.getPhone());
        assertEquals(thirtyChars, c.getAddress());
    }

    @Test
    public void testInvalidPhoneAdditionalCases() {
        // 9 digits
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "A", "B", "123456789", "Addr")
        );
        // 11 digits
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "A", "B", "12345678901", "Addr")
        );
        // non-digits
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "A", "B", "12345abcde", "Addr")
        );
    }

    @Test
    public void testSetterBoundaryLengthsAccepted() {
        Contact c = new Contact("9", "John", "Doe", "0123456789", "123 Any St");
        // exactly at limits should be accepted
        c.setFirstName("ABCDEFGHIJ"); // 10
        c.setLastName("ABCDEFGHIJ");  // 10
        c.setAddress("123456789012345678901234567890"); // 30
        assertEquals("ABCDEFGHIJ", c.getFirstName());
        assertEquals("ABCDEFGHIJ", c.getLastName());
        assertEquals("123456789012345678901234567890", c.getAddress());
    }
}
