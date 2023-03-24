package restraunt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import restraunt.resources.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static restraunt.Main.logger; // хуйня или не хуйня?

@NoArgsConstructor
public class RestaurantSimulation {
    ManagerAgent manager;
    List<MenuDish> dishes;
    List<CookAgent> cooks;
    List<KitchenFacilityType> facilityTypes;

    //List
    ObjectMapper objectMapper = new ObjectMapper();

    public void getCooks() throws IOException {
        File file = new File(Configurations.cooksPath); // мб выкинуть отсюда
        cooks = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getFacilityTypes() throws IOException {
        File file = new File(Configurations.facilityTypesPath); // мб выкинуть отсюда
        facilityTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getDishes() throws IOException {
        File file = new File(Configurations.dishesPath); // мб выкинуть отсюда
        dishes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public String serialize(Object o) throws JsonProcessingException {
        String serialized = objectMapper.writeValueAsString(o);
        return serialized;
    }

    public void getResources() {
        try {
            getCooks();
            getFacilityTypes();
            getDishes();
            System.out.println(serialize(dishes));
        } catch (Exception e) {
            logger.error("Failed to initialize resources", e);
        }
    }

    public void start() {
        ManagerAgent manager = new ManagerAgent();
        manager.setup();
    }
}
