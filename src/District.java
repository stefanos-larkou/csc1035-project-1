import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is a class for representing a district object. It has methods
 * relating to the abstraction of a district and the return of useful
 * information about the incidents stored in it.
 *
 * @author Stefanos Larkou
 */

public class District {

    private String name;
    private ArrayList<Incident> incidentsInDistrict;

    /**
     * The constructor of the district object.
     *
     * @param name Represents the name of the district.
     */

    public District(String name) {
        this.name = name;
        this.incidentsInDistrict = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Incident> getIncidents() {
        return incidentsInDistrict;
    }

    /**
     * This method overwrites the list of incidents in the district
     * with a new one.
     *
     * @param incidentsInDistrict The new list of incidents.
     */

    public void setIncidents(ArrayList<Incident> incidentsInDistrict) {
        this.incidentsInDistrict = incidentsInDistrict;
    }

    /**
     * This method adds an incident object to the list of incidents.
     *
     * @param i The incident to be added.
     */

    public void addIncident(Incident i){
        getIncidents().add(i);
    }

    /**
     * This method returns a readable representation of a district
     * object.
     *
     * @return String representation of a district object.
     */

    @Override
    public String toString(){
        return "District name:\t" + getName() + "\n"
                + "Incidents:\t\n" + getIncidents() + "\n";
    }

    /**
     * This method returns the incident with the highest value of goods
     * stolen in the district.
     *
     * @return Incident object with the highest value.
     */

    public Incident highestValue(){
        return Collections.max(getIncidents(), Comparator.comparing(Incident::getValue));
    }

    /**
     * This method returns the average value of goods stolen in the
     * district for a given year.
     *
     * @param year The year in question.
     *
     * @return Average value of incidents in that year.
     */

    public double averageValue(int year){
        int sum = 0;

        for (Incident incident : getIncidents()) {
            if(incident.getYear() == year) {
                sum += incident.getValue();
            }
        }

        return (double)sum / getIncidents().size();
    }

    /**
     * This method returns the incidents in the district that have a
     * larger value of goods stolen than the given amount.
     *
     * @param value The value to filter by.
     *
     * @return List of incident objects with value greater than the
     *         given value.
     */

    public ArrayList<Incident> filterGreaterThan(int value){
        ArrayList<Incident> filter = new ArrayList<>();
        for (Incident i: getIncidents()) {
            if (value < i.getValue()) {
                filter.add(i);
            }
        }
        return filter;
    }
}
