public class Incident{

    private String month, postcode;
    private int year, value;

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