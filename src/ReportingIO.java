import java.util.Arrays;
import java.util.Scanner;

public class ReportingIO {

    public static void main(String[] args){
        new ReportingIO().run();
    }

    public void run() {
        Reporting r = new Reporting();

        String[] months = new String[12];
        months[0] = "january";
        months[1] = "february";
        months[2] = "march";
        months[3] = "april";
        months[4] = "may";
        months[5] = "june";
        months[6] = "july";
        months[7] = "august";
        months[8] = "september";
        months[9] = "october";
        months[10] = "november";
        months[11] = "december";

        boolean quit = false;
        Scanner sc = new Scanner(System.in);

        while (!quit) {
            menu();

            System.out.println("Please select an option:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> recordDistrict(r);
                case 2 -> addToDistrict(recordIncident(months), r);
                case 3 -> reportLargestAverage(r);
                case 4 -> reportHighestValue(r);
                case 5 -> reportGreaterValue(r);
                case 6 -> displayAllDistrictNames(r);
                case 7 -> {
                    System.out.println("Quitting...");
                    quit = true;
                }
                default -> System.out.println("Not a valid option");
            }
        }
    }

    private void menu() {
        System.out.println("""
                1: Add a district.
                2: Add an incident.
                3: Report: District with largest average value incident for selected year 
                   (will return first found if more than one).
                4: Report: Highest value incident ever reported.
                5: Report: all incidents with value greater than a given amount.
                6: Display all district names.
                7: Quit program.
                              
                """);
    }

    private void recordDistrict(Reporting r){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter District's name: ");
        String name = sc.nextLine();

        boolean b = false;

        for(District d: r.getDistricts()){
            if (name.equals(d.getName())) {
                b = true;
                break;
            }
        }
        if (!b) {
            District d = new District(name);
            r.addDistrict(d);
            System.out.println("New district:\n" + d);
        }
        else{
            System.out.println("District already exists.\n");
        }
    }

    private Incident recordIncident(String[] months){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter month of incident: ");
        String month = sc.nextLine();
        while(Arrays.stream(months).noneMatch(month.toLowerCase()::contains)){
            System.out.println("Invalid month name, " +
                    "enter month of incident again: ");
            month = sc.nextLine();
        }
        month = month.substring(0,1).toUpperCase()+month.substring(1).toLowerCase();

        System.out.println("Enter year of incident: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.println("Enter postcode of incident: ");
        String postcode = sc.nextLine();

        System.out.println("Enter value of goods stolen: ");
        int value = sc.nextInt();

        return new Incident(month, postcode, year, value);
    }

    private void addToDistrict(Incident i, Reporting r){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter which district this incident belongs to: ");
        String district = sc.nextLine();

        boolean b = false;
        District temp = new District("");

        for(District d: r.getDistricts()){
            if(district.equals(d.getName())){
                d.addIncident(i);
                b = true;
                temp = d;
            }
        }

        if(!b) {
            System.out.println("Creating new district and adding incident...");
            District dis = new District(district);
            dis.addIncident(i);
            r.addDistrict(dis);
            System.out.println("New district: \n" + dis);
        }
        else{
            System.out.println("Updated district data:\n" + temp);
        }
    }

    private void reportLargestAverage(Reporting r) {
        Scanner sc = new Scanner(System.in);

        if (checkNullIncident(r) && checkNullDistrict(r)) {
            System.out.println("Enter year for report: ");
            int year = sc.nextInt();

            boolean exists = false;

            for (District d : r.getDistricts()) {
                for (Incident i : d.getIncidents()) {
                    if (i.getYear() == year) {
                        exists = true;
                        break;
                    }
                }
            }

            if (exists) {
                System.out.println("District with the largest average value " +
                        "of goods stolen for year " + year + ": " +
                        r.largestAverage(year).getName() + "\n");
            }
            else {
                System.out.println("No incidents were reported that year.\n");
            }
        }
        else if(!checkNullDistrict(r) && !checkNullIncident(r)){
            noDistricts();
        }
        else if(!checkNullIncident(r)) {
            noIncidents();
        }
    }

    private void reportHighestValue(Reporting r) {
        if (checkNullIncident(r) && checkNullDistrict(r)) {
            System.out.println("Highest value incident ever reported: \n" +
                    r.highestValueEver());

        }
        else if(!checkNullIncident(r) && !checkNullDistrict(r)){
            noDistricts();
        }
        else if(!checkNullIncident(r)){
            noIncidents();
        }
    }

    private void reportGreaterValue(Reporting r){
        Scanner sc = new Scanner(System.in);


        if(checkNullIncident(r) && checkNullDistrict(r)) {
            System.out.println("Enter value to filter by: ");
            int value = sc.nextInt();

            System.out.println(r.filterGreaterThan(value));
            System.out.println("\n");
        }
        else if(!checkNullIncident(r) && !checkNullDistrict(r)){
            noDistricts();
        }
        else if(!checkNullIncident(r)){
            noIncidents();
        }
    }

    private void displayAllDistrictNames(Reporting r){
        if(checkNullDistrict(r)) {
            for (District d : r.getDistricts()) {
                System.out.println(d.getName());
            }
            System.out.println("\n");
        }
        else{
            noDistricts();
        }
    }

    private boolean checkNullIncident(Reporting r){
        boolean b = false;
        for (District d : r.getDistricts()) {
            for(Incident i: d.getIncidents()){
                if (i != null) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    private boolean checkNullDistrict(Reporting r){

        return r.getDistricts().size() != 0;
    }

    private void noIncidents(){
        System.out.println("There are no reported incidents in any" +
                " district.\n");
    }

    private void noDistricts(){
        System.out.println("There are no districts.\n");
    }
}
