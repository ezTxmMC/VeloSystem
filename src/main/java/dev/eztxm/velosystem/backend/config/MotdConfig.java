package dev.eztxm.velosystem.backend.config;

import dev.eztxm.config.JsonConfig;
import dev.eztxm.velosystem.backend.util.ValueType;
import lombok.Getter;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MotdConfig {
    private final JsonConfig config;
    @Getter private List<String> lineOne;
    @Getter private List<String> lineTwo;
    @Getter private List<String> maintenanceLineOne;
    @Getter private List<String> maintenanceLineTwo;
    @Getter private List<String> maintenanceScreen;
    @Getter private boolean maintenance;


    public MotdConfig(JsonConfig config) {
        this.config = config;
        this.lineOne = (List<String>) getOrSet(ValueType.LIST, "Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.lineTwo = (List<String>) getOrSet(ValueType.LIST, "Line-2", List.of("&c&lNEW! &b&lCITYBUILD UPDATE"));
        this.maintenanceLineOne = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.maintenanceLineTwo = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-2", List.of("&c&lMAINTENANCE! &7We are in maintenance for the next major update"));
        this.maintenanceScreen = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Screen", List.of("&e&lYourServer.com", " ", "&7We are in &cmaintenance", "&7Check our twitter and discord for news!"));
        this.maintenance = (boolean) getOrSet(ValueType.BOOLEAN, "Maintenance", true);
    }

    public void update() {
        this.lineOne = (List<String>) getOrSet(ValueType.LIST, "Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.lineTwo = (List<String>) getOrSet(ValueType.LIST, "Line-2", List.of("&c&lNEW! &b&lCITYBUILD UPDATE"));
        this.maintenanceLineOne = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.maintenanceLineTwo = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-2", List.of("&c&lMAINTENANCE! &7We are in maintenance for the next major update"));
        this.maintenanceScreen = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Screen", List.of("&e&lYourServer.com", " ", "&7We are in &cmaintenance", "&7Check our twitter and discord for news!"));
        this.maintenance = (boolean) getOrSet(ValueType.BOOLEAN, "Maintenance", true);
    }

    private Object getOrSet(ValueType type, String key, Object set) {
        switch (type) {
            case STRING -> {
                String value = config.getString(key);
                if (value == null) {
                    config.setString(key, (String) set);
                    return config.getString(key);
                }
                return value;
            }
            case BOOLEAN -> {
                return config.getBoolean(key);
            }
            case LIST -> {
                List<String> value = new ArrayList<>();
                config.getJsonArray(key).toList().forEach(object -> value.add((String) object));
                if (value.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(set);
                    config.setJsonArray(key, jsonArray);
                    return config.getString(key);
                }
                return value;
            }
            default -> {
                return null;
            }
        }
    }
}
