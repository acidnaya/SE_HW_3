package restraunt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import restraunt.agent.Agent;
import restraunt.agent.AgentRepository;
import restraunt.resources.CustomerAgent;
import restraunt.resources.basic.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static restraunt.Main.logger; // хуйня или не хуйня?

@NoArgsConstructor
public class RestaurantSimulation {
    // public static AgentRepository repository;
    List<Agent> repository;

    public Time time;

    @Getter
    private ManagerAgent manager; // агент
    private List<Customer> customers; // агент
    private List<CookAgent> cooks; // агент
    private List<MenuDish> dishes; // не агент
    private List<ProductType> productTypes; // не агент и не меняется
    private List<Product> productItems; // не агент
    private List<DishCard> dishCards; // не агент и не меняется
    private List<KitchenOperationType> operations; // не агент и не меняется
    private List<KitchenFacilityType> facilityTypes; // не агент и не меняется
    private List<KitchenFacility> facilities; // ?? меняется
    private ObjectMapper objectMapper = new ObjectMapper();

    private void getCustomers() throws IOException {
        File file = new File(Configurations.customersPath); // мб выкинуть отсюда
        customers = objectMapper.readValue(file, new TypeReference<>() {});
        for (var customer: customers) {
            repository.add(new CustomerAgent(customer));
        }
    }

    private void getCooks() throws IOException {
        File file = new File(Configurations.cooksPath); // мб выкинуть отсюда
        cooks = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getFacilityTypes() throws IOException {
        File file = new File(Configurations.facilityTypesPath); // мб выкинуть отсюда
        facilityTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getFacilities() throws IOException {
        File file = new File(Configurations.facilityPath); // мб выкинуть отсюда
        facilities = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getDishes() throws IOException {
        File file = new File(Configurations.dishesPath); // мб выкинуть отсюда
        dishes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductTypes() throws IOException {
        File file = new File(Configurations.productTypesPath);
        productTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductItems() throws IOException {
        File file = new File(Configurations.productsPath);
        productItems = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getDishCards() throws IOException {
        File file = new File(Configurations.cardsPath);
        dishCards = objectMapper.readValue(file, new TypeReference<>() {});

    }

    private void getOperations() throws IOException {
        File file = new File(Configurations.operationTypesPath);
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

    // сделать проверку после оформления каждого заказа, на наличие продуктов и исключить из меню то че нельзя приготовить

    public void start() {
        time = new Time();
        manager = new ManagerAgent();
        manager.setup();
        time.start(time);
        manager.start(manager);
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
