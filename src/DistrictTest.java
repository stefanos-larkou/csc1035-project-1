import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistrictTest {

    District mockDistrict = new District("Newcastle");

    @Test
    void getName() {
        assertEquals("Newcastle", mockDistrict.getName());
    }

    @Test
    void setName() {
        mockDistrict.setName("Essex");
        assertEquals("Essex", mockDistrict.getName());
    }

    @Test
    void addIncident() {
        Incident mockAddition = new Incident("December", "AB1 2CD", 2020, 123);
        assert mockDistrict.getIncidents().size() == 0;
        mockDistrict.addIncident(mockAddition);
        assert mockDistrict.getIncidents().size() == 1;
    }

    @Test
    void getIncidents() {
        Incident mockAddition = new Incident("December", "AB1 2CD", 2020, 123);
        mockDistrict.addIncident(mockAddition);
        assertEquals(mockAddition.getValue(), mockDistrict.getIncidents().get(0).getValue());
        assertEquals(mockAddition.getMonth(), mockDistrict.getIncidents().get(0).getMonth());
        assertEquals(mockAddition.getYear(), mockDistrict.getIncidents().get(0).getYear());
        assertEquals(mockAddition.getPostcode(), mockDistrict.getIncidents().get(0).getPostcode());
    }

    @Test
    void setIncidents() {
        Incident mockAddition = new Incident("December", "AB1 2CD", 2021, 123);
        ArrayList<Incident> mockList = new ArrayList<>();
        mockList.add(mockAddition);
        assert mockDistrict.getIncidents().size() == 0;
        mockDistrict.setIncidents(mockList);
        assert mockDistrict.getIncidents().size() == 1;
        assertEquals(mockAddition.getValue(), mockDistrict.getIncidents().get(0).getValue());
        assertEquals(mockAddition.getMonth(), mockDistrict.getIncidents().get(0).getMonth());
        assertEquals(mockAddition.getYear(), mockDistrict.getIncidents().get(0).getYear());
        assertEquals(mockAddition.getPostcode(), mockDistrict.getIncidents().get(0).getPostcode());
    }

    @Test
    void highestValue() {
        Incident mockAddition1 = new Incident("December", "AB1 2CD", 2020, 123);
        Incident mockAddition2 = new Incident("February", "EF3 4GH", 2010, 213);
        Incident mockAddition3 = new Incident("June", "IJ5 6KL", 2015, 321);
        mockDistrict.addIncident(mockAddition1);
        mockDistrict.addIncident(mockAddition2);
        mockDistrict.addIncident(mockAddition3);
        assertEquals(321, mockDistrict.highestValue().getValue());
    }

    @Test
    void averageValue() {
        Incident mockAddition1 = new Incident("December", "AB1 2CD", 2020, 123);
        Incident mockAddition2 = new Incident("February", "EF3 4GH", 2020, 213);
        Incident mockAddition3 = new Incident("June", "IJ5 6KL", 2019, 321);
        mockDistrict.addIncident(mockAddition1);
        mockDistrict.addIncident(mockAddition2);
        mockDistrict.addIncident(mockAddition3);
        assertEquals(168.0, mockDistrict.averageValue(2020));
    }

    @Test
    void filterGreaterThan() {
        Incident mockAddition1 = new Incident("December", "AB1 2CD", 2020, 123);
        Incident mockAddition2 = new Incident("February", "EF3 4GH", 2020, 213);
        Incident mockAddition3 = new Incident("June", "IJ5 6KL", 2019, 321);
        mockDistrict.addIncident(mockAddition1);
        mockDistrict.addIncident(mockAddition2);
        mockDistrict.addIncident(mockAddition3);
        ArrayList<Incident> filter = new ArrayList<>();
        filter.add(mockAddition2);
        filter.add(mockAddition3);
        assertEquals(filter, mockDistrict.filterGreaterThan(200));
    }
}