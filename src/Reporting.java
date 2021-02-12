import java.util.ArrayList;
import java.util.Collections;

public class Reporting {

    private ArrayList<District> districts;

    public Reporting(ArrayList<District> districts) {
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
}