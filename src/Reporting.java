import java.util.ArrayList;

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
}
