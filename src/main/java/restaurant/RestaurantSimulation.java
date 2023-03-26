package restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import restaurant.agents.*;
import restaurant.messages.Message;
import restaurant.resources.additional.OperationLog;
import restaurant.resources.basic.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Slf4j
public class RestaurantSimulation {
    private final List<OperationLog> outputLog = new ArrayList<>();
    private List<Agent<Message>> localRepository;
    public Time time;
    @Getter
    private ManagerAgent manager;
    @Getter
    private WarehouseAgent warehouse;
    @Getter
    private CookAgent cook;
    @Getter
    private FacilityAgent facility;
    @Getter
    private List<MenuDish> dishes;
    private List<Product> productItems;
    @Getter
    private List<DishCard> dishCards;
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void addLog(OperationLog l) {
        outputLog.add(l.createCopy());
    }

    public void writeLog() {
        try {
            var fw = new FileWriter(Config.outputPath);
            PrintWriter writer = new PrintWriter(fw);
            var s = serialize(outputLog);
            writer.write(s);
            fw.close();
            log.info("OPERATIONS OUTPUT LOG WAS WRITTEN TO {}", Config.outputPath);
        } catch (Exception e) {
            log.error("Could not write output operations log...");
        }
    }
    private void getCustomersJSON() throws IOException {
        File file = new File(Config.customersPath);
        List<Customer> customers = objectMapper.readValue(file, new TypeReference<>() {
        });
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
        File file = new File(Config.facilityTypesPath);
        List<KitchenFacilityType> facilityTypes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getFacilitiesJSON() throws IOException {
        File file = new File(Config.facilityPath);
        List<KitchenFacility> facilities = objectMapper.readValue(file, new TypeReference<>() {});
        facility = new FacilityAgent(facilities);
    }

    private void getDishesJSON() throws IOException {
        File file = new File(Config.dishesPath);
        dishes = objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void getProductTypesJSON() throws IOException {
        File file = new File(Config.productTypesPath);
        List<ProductType> productTypes = objectMapper.readValue(file, new TypeReference<>() {
        });
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
        List<KitchenOperationType> operations = objectMapper.readValue(file, new TypeReference<>() {
        });
    }

    private String serialize(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
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
        for (var agent: localRepository) {
            Agent.start(agent);
        }
    }
}
