import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the class that runs the program. It provides a user-friendly
 * menu with a set of options to add and display information about
 * incidents and districts, as well as interesting information about
 * them.
 *
 * @author Stefanos Larkou
 */

public class ReportingIO {

    public static void main(String[] args){
        new ReportingIO().run();
    }

    /**
     * The program's run method. It determines what happens with each
     * option picked from the menu.
     */

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
                case 7 -> displayAllIncidents(r);
                case 8 -> displayDistrictIncidents(r);
                case 9 -> {
                    System.out.println("Quitting...");
                    quit = true;
                }
                default -> System.out.println("Not a valid option");
            }
        }
    }

    /**
     * The list of options.
     */

    private void menu() {
        System.out.println("""
                1: Add a district.
                2: Add an incident.
                3: Report: District with largest average value incident for selected year 
                   (will return first found if more than one).
                4: Report: Highest value incident ever reported.
                5: Report: all incidents with value greater than a given amount.
                6: Display all district names.
                7: Display all incidents in their respective districts.
                8: Display all incidents recorded in selected district.
                9: Quit program.
                              
                """);
    }

    /**
     * This method takes a name for a new district. If that district
     * already exists the user is notified and is taken back to the
     * menu, otherwise a new empty district is created and stored in
     * the reporting object.
     *
     * @param r The reporting object in which the new district will be
     *          stored.
     */

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

    /**
     * This method takes from the user all necessary information to
     * create a new incident object. For the month field, it compares
     * the input with an existing array of months (located in the run()
     * method) and will not proceed unless the String entered matches
     * one in the months array when made all lower-case. If it does,
     * the first letter is converted to upper-case and the rest to
     * lower-case for uniformity (eg "dEcemBEr" will be converted to
     * "December"). If it does not, the user will keep being asked
     * until they enter a valid month name.
     *
     * @param months The array of months to compare the input to.
     *
     * @return A new incident object.
     */

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

    /**
     * After creating an incident object, the user will be asked to add
     * it to a district. This method does exactly that. If the district
     * name entered matches an existing district it will be added
     * there, otherwise it will be added to a new one with that name.
     *
     * @param i The incident to be added.
     * @param r The reporting object to access the districts and store
     *          the new district, if one is created.
     */

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

    /**
     * This method prints out the name of the district with the largest
     * average value of goods stolen for a year of the user's choice,
     * after checking that one exists for that to be done. If it
     * cannot, an appropriate message is displayed and the user is
     * taken back to the menu.
     *
     * @param r The reporting object to access the districts.
     */

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

    /**
     * This method prints out the incident with the highest value ever,
     * after checking that one exists. Otherwise, an appropriate
     * message is displayed and the user is returned to the menu.
     *
     * @param r The reporting object to access the districts.
     */

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

    /**
     * This method prints all incidents (in their respective districts)
     * with a value of goods stolen greater than the one entered by the
     * user, after checking that there are incidents to filter.
     * Otherwise, an appropriate message is displayed and the user is
     * returned to the menu.
     *
     * @param r The reporting object to access the districts.
     */

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

    /**
     * This method prints out the names of all existing districts,
     * after checking that at least one exists. Otherwise, an
     * appropriate message is displayed and the user is returned to the
     * menu.
     * @param r The reporting object to access the districts.
     */

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

    /**
     * This method prints out all incidents (in their respective
     * districts), after checking that there is information to print.
     * If not, an appropriate message is displayed and the user is
     * returned to the menu.
     *
     * @param r The reporting object to access the districts.
     */

    private void displayAllIncidents(Reporting r){
        if(checkNullDistrict(r) && checkNullIncident(r)){
            for(District d: r.getDistricts()){
                System.out.println(d);
            }
            System.out.println("\n");
        }
        else if(!checkNullDistrict(r) && !checkNullIncident(r)){
            noDistricts();
        }
        else if(!checkNullIncident(r)){
            noIncidents();
        }
    }

    /**
     * This method prints out all incidents in a district of the user's
     * choice. If the one entered does not match any of the existing
     * districts, an appropriate message is displayed and the user
     * is returned to the menu.
     *
     * @param r The reporting object to access the districts.
     */

    private void displayDistrictIncidents(Reporting r){
        Scanner sc = new Scanner(System.in);

        System.out.println("Select district: ");
        String name = sc.nextLine();

        boolean b = false;
        ArrayList<Incident> temp = null;

        for(District d: r.getDistricts()){
            if(name.equals(d.getName())){
                b = true;
                temp = d.getIncidents();
                break;
            }
        }

        if(b){
            for(Incident i: temp){
                System.out.println(i);
            }
        }
        else{
            System.out.println("District doesn't exist.");
        }
        System.out.println("\n");
    }

    /**
     * This method checks if an incident exists in any district. Used
     * several times in other methods to avoid repetition.
     *
     * @param r The reporting object to access the districts.
     *
     * @return True if at least one exists, false if not.
     */

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

    /**
     * This method checks if at least one district exists. Used several
     * times in other methods to avoid repetition.
     *
     * @param r The reporting object to access the districts.
     *
     * @return True if there is at least one district, false if not.
     */

    private boolean checkNullDistrict(Reporting r){

        return r.getDistricts().size() != 0;
    }

    /**
     * This method prints a message to inform the user that there are
     * no reported incidents at all. Used several times in other
     * methods to avoid repetition.
     */

    private void noIncidents(){
        System.out.println("There are no reported incidents in any" +
                " district.\n");
    }

    /**
     * This method prints a message to inform the user that no
     * districts exist. Used several times in other methods to avoid
     * repetition.
     */

    private void noDistricts(){
        System.out.println("There are no districts.\n");
    }
}
