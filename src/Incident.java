/**
 *
 * This is a class for representing an incident object. It has methods
 * relating to the abstraction of an incident.
 *
 * @author Stefanos Larkou
 */

public class Incident{

    private String month, postcode;
    private int year, value;

    /**
     * The constructor of the Incident object.
     *
     * @param month Represents the month when the incident took place.
     *
     * @param postcode Represents the postcode of the location where
     *                 the incident took place.
     *
     * @param year Represents the year when the incident took place.
     *
     * @param value Represents the value of the goods stolen during the
     *              incident.
     */

    public Incident(String month, String postcode, int year, int value) {
        this.month = month;
        this.postcode = postcode;
        this.year = year;
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * This method returns a readable representation of an Incident
     * object.
     *
     * @return String representation of an Incident object.
     */

    @Override
    public String toString(){
        return "Incident{\n"
                + "Value of goods stolen:\t" + getValue() + "\n"
                + "Month:\t" + getMonth() + "\n"
                + "Year:\t" + getYear() + "\n"
                + "Postcode:\t" + getPostcode() + "\n"
                + "}";
    }
}