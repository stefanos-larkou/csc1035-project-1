import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is a class for representing a reporting object. It has methods
 * relating to the abstraction of a district and the return of useful
 * information about the districts stored in it.
 *
 * @author Stefanos Larkou
 */

public class Reporting {

    private ArrayList<District> districts;

    /**
     * The constructor of the reporting object.
     */

    public Reporting() {
        this.districts = new ArrayList<>();
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }

    /**
     * This method adds a district object to the list of districts
     *
     * @param d The district to be added.
     */

    public void addDistrict(District d) {
        getDistricts().add(d);
    }

    /**
     * This method returns the district with the largest average value
     * of goods stolen for a given year.
     *
     * @param year The year in question.
     *
     * @return District object with the largest average in that year.
     */

    public District largestAverage(int year) {
        ArrayList<Double> average = new ArrayList<>();

        for(District d: getDistricts()){
            average.add(d.averageValue(year));
        }

        double largestAverage = Collections.max(average);

        for(District d: getDistricts()){
            if(d.averageValue(year) == largestAverage){
                return d;
            }
        }
        return null;
    }

    /**
     * This method returns the incident with the highest value of goods
     * stolen ever.
     *
     * @return Incident object with the greatest value.
     */

    public Incident highestValueEver() {
        ArrayList<Incident> temp = new ArrayList<>();
        for (District d : getDistricts()) {
            temp.add(d.highestValue());
        }
        return Collections.max(temp, Comparator.comparing(Incident::getValue));
    }

    /**
     * This method returns the incidents with value of goods stolen
     * greater than a given amount (inside their respective districts).
     *
     * @param value The value to filter by.
     *
     * @return List of the existing district objects with only the
     *         incidents with value greater than the given value.
     */

    public ArrayList<District> filterGreaterThan(int value) {
        ArrayList<District> filter = new ArrayList<>();

        for(int i = 0; i < getDistricts().size(); i++){
            filter.add(new District(getDistricts().get(i).getName()));
            filter.get(i).setIncidents(getDistricts().get(i).filterGreaterThan(value));
        }

        return filter;
    }
}
