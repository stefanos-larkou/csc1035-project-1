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

    public double averageValue(int year){
        int sum = 0;

        for (Incident incident : getIncidents()) {
            if(incident.getYear() == year) {
                sum += incident.getValue();
            }
        }

        return (double)sum / getIncidents().size();
    }

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
