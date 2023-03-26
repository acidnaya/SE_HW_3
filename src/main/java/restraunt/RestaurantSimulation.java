package restraunt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import restraunt.agents.*;
import restraunt.resources.basic.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Slf4j
public class RestaurantSimulation {
    private List<Agent> localRepository;
    public Time time;
    @Getter
    private ManagerAgent manager;
    @Getter
    private WarehouseAgent warehouse;
    @Getter
    private CookAgent cook;
    @Getter
    private FacilityAgent facility;
    private List<Customer> customers;
    @Getter
    private List<MenuDish> dishes;
    private List<ProductType> productTypes;
    private List<Product> productItems;
    @Getter
    public List<DishCard> dishCards;
    private List<KitchenOperationType> operations;
    private List<KitchenFacilityType> facilityTypes;
    private ObjectMapper objectMapper = new ObjectMapper();

    private void getCustomersJSON() throws IOException {
        File file = new File(Config.customersPath);
        customers = objectMapper.readValue(file, new TypeReference<>() {});
        for (var customer: customers) {
            localRepository.add(new CustomerAgent(customer));
        }
    }

    private void getCooksJSON() throws IOException {
        File file = new File(Config.cooksPath);
        List<Cook> cooks = objectMapper.readValue(file, new TypeReference<>() {});
        cook = new CookAgent(cooks);
    }

    private void getFacilityTypesJSON() throws IOException {
        File file = new File(Config.facilityTypesPath); // мб выкинуть отсюда
        facilityTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getFacilitiesJSON() throws IOException {
        File file = new File(Config.facilityPath); // мб выкинуть отсюда
        List<KitchenFacility> facilities = objectMapper.readValue(file, new TypeReference<>() {});
        facility = new FacilityAgent(facilities);
    }

    private void getDishesJSON() throws IOException {
        File file = new File(Config.dishesPath); // мб выкинуть отсюда
        dishes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductTypesJSON() throws IOException {
        File file = new File(Config.productTypesPath);
        productTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductItemsJSON() throws IOException {
        File file = new File(Config.productsPath);
        productItems = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getDishCardsJSON() throws IOException {
        File file = new File(Config.cardsPath);
        dishCards = objectMapper.readValue(file, new TypeReference<>() {});

    }

    private void getOperationsJSON() throws IOException {
        File file = new File(Config.operationTypesPath);
        operations = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private String serialize(Object o) throws JsonProcessingException {
        String serialized = objectMapper.writeValueAsString(o);
        return serialized;
    }

    public void getResources() {
        localRepository = new ArrayList<>();
        try {
            getDishCardsJSON();
            getCooksJSON();
            getFacilityTypesJSON();
            getProductTypesJSON();
            getProductItemsJSON();
            getDishesJSON();
            getFacilitiesJSON();
            getOperationsJSON();
            getCustomersJSON();
        } catch (Exception e) {
            log.error("Failed to initialize resources", e);
        }
    }

    public void start() {
        time = new Time();
        manager = new ManagerAgent();
        warehouse = new WarehouseAgent(productItems);
        Agent.start(time);
        Agent.start(warehouse);
        Agent.start(manager);
        Agent.start(cook);
        for (var agent: localRepository) {
            Agent.start(agent);
        }
//        try {
//            for (int i = 0; i < 10; ++i) {
//                System.out.println("time: " + time.timeToString(time.getCurrentTime()));
//                Thread.sleep(500);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
