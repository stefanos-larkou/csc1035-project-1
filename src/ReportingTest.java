import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReportingTest {

    Reporting mockReporting = new Reporting();

    @Test
    void addDistrict() {
        District mockAddition = new District("Newcastle");

        assert mockReporting.getDistricts().size() == 0;

        mockReporting.addDistrict(mockAddition);

        assert mockReporting.getDistricts().size() == 1;
    }

    @Test
    void getDistricts() {
        District mockAddition = new District("Newcastle");

        ArrayList<Incident> mockList = new ArrayList<>();

        mockList.add(new Incident("December", "AB1 2CD", 2020, 123));
        mockAddition.setIncidents(mockList);

        mockReporting.addDistrict(mockAddition);

        assertEquals(mockAddition.getName(), mockReporting.getDistricts().get(0).getName());
        assertEquals(mockAddition.getIncidents(), mockReporting.getDistricts().get(0).getIncidents());
    }

    @Test
    void setDistricts() {
        District mockAdditionD = new District("Newcastle");

        Incident mockAdditionI = new Incident("December", "AB1 2CD", 2020, 123);

        ArrayList<District> mockListD = new ArrayList<>();
        ArrayList<Incident> mockListI = new ArrayList<>();

        mockListD.add(mockAdditionD);
        mockListI.add(mockAdditionI);

        assert mockReporting.getDistricts().size() == 0;

        mockReporting.setDistricts(mockListD);
        mockReporting.getDistricts().get(0).setIncidents(mockListI);

        assert mockReporting.getDistricts().size() == 1;

        assertEquals(mockAdditionD.getName(), mockReporting.getDistricts().get(0).getName());
        assertEquals(mockAdditionD.getIncidents(), mockReporting.getDistricts().get(0).getIncidents());
    }

    @Test
    void largestAverage() {
        ArrayList<District> mockListD = new ArrayList<>();

        ArrayList<Incident> mockListI1 = new ArrayList<>();
        ArrayList<Incident> mockListI2 = new ArrayList<>();
        ArrayList<Incident> mockListI3 = new ArrayList<>();

        District mockAdditionD1 = new District("Newcastle");
        District mockAdditionD2 = new District("Essex");
        District mockAdditionD3 = new District("Canterbury");

        Incident mockAdditionI1 = new Incident("January", "AB1 2CD", 2020, 123);
        Incident mockAdditionI2 = new Incident("February", "EF3 4GH", 2020, 321);
        Incident mockAdditionI3 = new Incident("March", "IJ5 6KL", 2021, 213);
        Incident mockAdditionI4 = new Incident("April", "MN7 8OP", 2020, 456);
        Incident mockAdditionI5 = new Incident("May", "QR9 0ST", 2021, 0);
        Incident mockAdditionI6 = new Incident("June", "UV1 2XY", 2020, 999);
        Incident mockAdditionI7 = new Incident("July", "ZA3 4BC", 2021, 9999);
        Incident mockAdditionI8 = new Incident("August", "DE5 6FG", 2020, 124);
        Incident mockAdditionI9 = new Incident("September", "HI7 8JK", 2020, 467);

        mockListD.add(mockAdditionD1);
        mockListD.add(mockAdditionD2);
        mockListD.add(mockAdditionD3);

        mockListI1.add(mockAdditionI1);
        mockListI1.add(mockAdditionI2);
        mockListI1.add(mockAdditionI3);
        mockListI2.add(mockAdditionI4);
        mockListI2.add(mockAdditionI5);
        mockListI2.add(mockAdditionI6);
        mockListI3.add(mockAdditionI7);
        mockListI3.add(mockAdditionI8);
        mockListI3.add(mockAdditionI9);

        mockListD.get(0).setIncidents(mockListI1);
        mockListD.get(1).setIncidents(mockListI2);
        mockListD.get(2).setIncidents(mockListI3);

        mockReporting.setDistricts(mockListD);

        assertEquals(727.5, mockReporting.largestAverage(2020).averageValue(2020));
    }

    @Test
    void highestValueEver() {
        ArrayList<District> mockListD = new ArrayList<>();

        ArrayList<Incident> mockListI1 = new ArrayList<>();
        ArrayList<Incident> mockListI2 = new ArrayList<>();

        Incident mockAdditionI1 = new Incident("January", "AB1 2CD", 2020, 123);
        Incident mockAdditionI2 = new Incident("February", "EF3 4GH", 2020, 321);
        Incident mockAdditionI3 = new Incident("March", "IJ5 6KL", 2021, 213);

        District mockAdditionD1 = new District("Newcastle");
        District mockAdditionD2 = new District("Essex");

        mockListD.add(mockAdditionD1);
        mockListD.add(mockAdditionD2);

        mockListI1.add(mockAdditionI1);
        mockListI2.add(mockAdditionI2);
        mockListI2.add(mockAdditionI3);

        mockListD.get(0).setIncidents(mockListI1);
        mockListD.get(1).setIncidents(mockListI2);

        mockReporting.setDistricts(mockListD);

        assertEquals(321, mockReporting.highestValueEver().getValue());
    }

    @Test
    void filterGreaterThan() {
        ArrayList<District> mockListD = new ArrayList<>();
        ArrayList<District> mockListExpected= new ArrayList<>();

        ArrayList<Incident> mockListI1 = new ArrayList<>();
        ArrayList<Incident> mockListI2 = new ArrayList<>();

        Incident mockAdditionI1 = new Incident("January", "AB1 2CD", 2020, 123);
        Incident mockAdditionI2 = new Incident("February", "EF3 4GH", 2020, 321);
        Incident mockAdditionI3 = new Incident("March", "IJ5 6KL", 2021, 199);

        District mockAdditionD1 = new District("Newcastle");
        District mockAdditionD2 = new District("Essex");

        mockListD.add(mockAdditionD1);
        mockListD.add(mockAdditionD2);

        mockListI1.add(mockAdditionI1);
        mockListI2.add(mockAdditionI2);
        mockListI2.add(mockAdditionI3);

        mockListD.get(0).setIncidents(mockListI1);
        mockListD.get(1).setIncidents(mockListI2);

        mockListExpected.add(mockAdditionD1);
        mockListExpected.add(mockAdditionD2);

        mockListExpected.get(0).getIncidents().remove(mockAdditionI1);
        mockListExpected.get(1).getIncidents().remove(mockAdditionI3);

        mockReporting.setDistricts(mockListD);

        assertEquals(mockListExpected.get(0).getName(), mockReporting.filterGreaterThan(200).get(0).getName());
        assertEquals(mockListExpected.get(0).getIncidents(), mockReporting.filterGreaterThan(200).get(0).getIncidents());
        assertEquals(mockListExpected.get(1).getName(), mockReporting.filterGreaterThan(200).get(1).getName());
        assertEquals(mockListExpected.get(1).getIncidents(), mockReporting.filterGreaterThan(200).get(1).getIncidents());
    }
}