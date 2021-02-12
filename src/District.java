import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class District {

    private String name;
    private ArrayList<Incident> incidentsInDistrict;

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

    public void setIncidents(ArrayList<Incident> incidentsInDistrict) {
        this.incidentsInDistrict = incidentsInDistrict;
    }

    public void addIncident(Incident i){
        getIncidents().add(i);
    }

    public String toString(){
        return "District name:\t" + getName() + "\n"
                + "Incidents:\t\n" + getIncidents() + "\n";
    }

    public Incident highestValue(){
        return Collections.max(getIncidents(), Comparator.comparing(Incident::getValue));
    }
}
