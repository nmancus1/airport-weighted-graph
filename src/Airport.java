/**
 * This class represents an airport
 */
public class Airport {

    //Data fields
    String name;
    private int layOver = 2;


    //Accessors and Mutators
    public Airport() {
        name = null;
    }

    public Airport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayover() {
        return layOver;
    }

    @Override
    public String toString() {
        return name;
    }
}
