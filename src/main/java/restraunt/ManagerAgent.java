package restraunt;

public class ManagerAgent {
    CookAgent cook;

    protected void setup() {

        System.out.println("Hello, I am a manager!");
        cook = new CookAgent(1, "John", true);
        cook.setup();
    }
}