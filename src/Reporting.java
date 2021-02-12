import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Reporting {

    private ArrayList<District> districts;

    public Reporting() {
        this.districts = new ArrayList<>();
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }

    public void addDistrict(District d) {
        districts.add(d);
    }

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

    public Incident highestValueEver() {
        ArrayList<Incident> temp = new ArrayList<>();
        for (District d : getDistricts()) {
            temp.add(d.highestValue());
        }
        return Collections.max(temp, Comparator.comparing(Incident::getValue));
    }

    public ArrayList<District> filterGreaterThan(int value) {
        ArrayList<District> filter = new ArrayList<>();

        for(int i = 0; i < getDistricts().size(); i++){
            filter.add(new District(getDistricts().get(i).getName()));
            filter.get(i).setIncidents(getDistricts().get(i).filterGreaterThan(value));
        }

        return filter;
    }
}
