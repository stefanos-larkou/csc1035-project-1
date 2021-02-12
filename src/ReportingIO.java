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
                case 3 -> {
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
                3: Quit program.
              
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
}
