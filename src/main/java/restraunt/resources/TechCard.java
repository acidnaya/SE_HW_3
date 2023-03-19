package restraunt.resources;

import java.util.List;

public class TechCard {
    int ID;
    String name;
    String description;
    double time;
    KitchenFacilityType equipment; // мб список?
    List<KitchenOperation> operations;
}
