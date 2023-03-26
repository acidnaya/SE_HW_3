package restraunt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import restraunt.agents.*;
import restraunt.resources.basic.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static restraunt.Main.logger; // хуйня или не хуйня?

@NoArgsConstructor
public class RestaurantSimulation {
    // public static AgentRepository repository;
    List<Agent> repository;

    public Time time;

    @Getter
    private ManagerAgent manager;
    @Getter
    private WarehouseAgent warehouse;
    private List<Customer> customers;
    private List<Cook> cooks;
    private List<MenuDish> dishes;
    private List<ProductType> productTypes;
    private List<Product> productItems;
    private List<DishCard> dishCards;
    private List<KitchenOperationType> operations;
    private List<KitchenFacilityType> facilityTypes;
    private List<KitchenFacility> facilities;
    private ObjectMapper objectMapper = new ObjectMapper();

    private void getCustomers() throws IOException {
        File file = new File(Config.customersPath); // мб выкинуть отсюда
        customers = objectMapper.readValue(file, new TypeReference<>() {});
        for (var customer: customers) {
            repository.add(new CustomerAgent(customer));
        }
    }

    private void getCooks() throws IOException {
        File file = new File(Config.cooksPath); // мб выкинуть отсюда
        cooks = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getFacilityTypes() throws IOException {
        File file = new File(Config.facilityTypesPath); // мб выкинуть отсюда
        facilityTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getFacilities() throws IOException {
        File file = new File(Config.facilityPath); // мб выкинуть отсюда
        facilities = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getDishes() throws IOException {
        File file = new File(Config.dishesPath); // мб выкинуть отсюда
        dishes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductTypes() throws IOException {
        File file = new File(Config.productTypesPath);
        productTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductItems() throws IOException {
        File file = new File(Config.productsPath);
        productItems = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getDishCards() throws IOException {
        File file = new File(Config.cardsPath);
        dishCards = objectMapper.readValue(file, new TypeReference<>() {});

    }

    private void getOperations() throws IOException {
        File file = new File(Config.operationTypesPath);
        operations = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private String serialize(Object o) throws JsonProcessingException {
        String serialized = objectMapper.writeValueAsString(o);
        return serialized;
    }

    public void getResources() {
        repository = new ArrayList<>();
        try {
            getDishCards();
            getCooks();
            getFacilityTypes();
            getProductTypes();
            getProductItems();
            getDishes();
            getFacilities();
            getOperations();
            getCustomers();
        } catch (Exception e) {
            logger.error("Failed to initialize resources", e);
        }
    }

    public void start() {
        time = new Time();
        manager = new ManagerAgent();
        warehouse = new WarehouseAgent(productItems, productTypes);
        Agent.start(time);
        Agent.start(warehouse);
        Agent.start(manager);
        for (var agent: repository) {
            agent.start(agent);
        }
        try {
            for (int i = 0; i < 10; ++i) {
                System.out.println("time: " + time.timeToString(time.getCurrentTime()));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
