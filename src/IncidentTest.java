import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IncidentTest {

    Incident mockIncident = new Incident("December", "AB1 2CD", 2020, 123);

    @Test
    void getMonth() {
        assertEquals("December", mockIncident.getMonth());
    }

    @Test
    void setMonth() {
        mockIncident.setMonth("November");
        assertEquals("November", mockIncident.getMonth());
    }

    @Test
    void getPostcode() {
        assertEquals("AB1 2CD", mockIncident.getPostcode());
    }

    @Test
    void setPostcode() {
        mockIncident.setPostcode("EF3 4GH");
        assertEquals("EF3 4GH", mockIncident.getPostcode());
    }

    @Test
    void getYear() {
        assertEquals(2020, mockIncident.getYear());
    }

    @Test
    void setYear() {
        mockIncident.setYear(2019);
        assertEquals(2019, mockIncident.getYear());
    }

    @Test
    void getValue() {
        assertEquals(123, mockIncident.getValue());
    }

    @Test
    void setValue() {
        mockIncident.setValue(321);
        assertEquals(321, mockIncident.getValue());
    }
}