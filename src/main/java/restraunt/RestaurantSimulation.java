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
    List<ProductType> productTypes;
    List<Product> productItems;
    List<KitchenFacilityType> facilityTypes;
    List<KitchenFacility> facilities;

    List<Customer> customers;

    //List
    ObjectMapper objectMapper = new ObjectMapper();

    public void getCustomers() throws IOException {
        File file = new File(Configurations.customersPath); // мб выкинуть отсюда
        customers = objectMapper.readValue(file, new TypeReference<>() {});
    }
    public void getCooks() throws IOException {
        File file = new File(Configurations.cooksPath); // мб выкинуть отсюда
        cooks = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getFacilityTypes() throws IOException {
        File file = new File(Configurations.facilityTypesPath); // мб выкинуть отсюда
        facilityTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getFacilities() throws IOException {
        File file = new File(Configurations.facilityPath); // мб выкинуть отсюда
        facilities = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getDishes() throws IOException {
        File file = new File(Configurations.dishesPath); // мб выкинуть отсюда
        dishes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getProductTypes() throws IOException {
        File file = new File(Configurations.productTypesPath);
        productTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void getProductItems() throws IOException {
        File file = new File(Configurations.productsPath);
        productItems = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public String serialize(Object o) throws JsonProcessingException {
        String serialized = objectMapper.writeValueAsString(o);
        return serialized;
    }

    public void getResources() {
        try {
            getCooks();
            getFacilityTypes();
            getProductTypes();
            getProductItems();
            getDishes();
            getFacilities();
            getCustomers();
            System.out.println(serialize(customers));
//            System.out.println(serialize(facilities));
//            System.out.println(serialize(dishes));
//            System.out.println(serialize(productTypes));
        } catch (Exception e) {
            logger.error("Failed to initialize resources", e);
        }
    }

    // сделать проверку после оформления каждого заказа, на наличие продуктов и исключить из меню то че нельзя приготовить

    public void start() {
        ManagerAgent manager = new ManagerAgent();
        manager.setup();
    }
}
