/**
 * This class represents an airport
 */
public class Airport {

    String name;
    private static final int waitTime = 2;


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

    public static int getWaitTime() {
        return waitTime;
    }

    @Override
    public String toString() {
        return name;
    }
}
