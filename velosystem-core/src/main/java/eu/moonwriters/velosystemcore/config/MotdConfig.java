package eu.moonwriters.velosystemcore.config;

import dev.eztxm.config.JsonConfig;
import eu.moonwriters.velosystemcore.util.ValueType;
import lombok.Getter;

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


    @SuppressWarnings("unchecked")
    public MotdConfig(JsonConfig config) {
        this.config = config;
        this.lineOne = (List<String>) getOrSet(ValueType.LIST, "Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.lineTwo = (List<String>) getOrSet(ValueType.LIST, "Line-2", List.of("&c&lNEW! &b&lCITYBUILD UPDATE"));
        this.maintenanceLineOne = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.maintenanceLineTwo = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-2", List.of("&c&lMAINTENANCE! &7We are in maintenance for the next major update"));
        this.maintenanceScreen = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Screen", List.of("&e&lYourServer.com", " ", "&7We are in &cmaintenance", "&7Check our twitter and discord for news!"));
        this.maintenance = (boolean) getOrSet(ValueType.BOOLEAN, "Maintenance", true);
    }

    @SuppressWarnings("unchecked")
    public void update() {
        this.lineOne = (List<String>) getOrSet(ValueType.LIST, "Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.lineTwo = (List<String>) getOrSet(ValueType.LIST, "Line-2", List.of("&c&lNEW! &b&lCITYBUILD UPDATE"));
        this.maintenanceLineOne = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-1", List.of("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]"));
        this.maintenanceLineTwo = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Line-2", List.of("&c&lMAINTENANCE! &7We are in maintenance for the next major update"));
        this.maintenanceScreen = (List<String>) getOrSet(ValueType.LIST, "Maintenance-Screen", List.of("&e&lYourServer.com", " ", "&7We are in &cmaintenance", "&7Check our twitter and discord for news!"));
        this.maintenance = (boolean) getOrSet(ValueType.BOOLEAN, "Maintenance", true);
    }

    private Object getOrSet(ValueType type, String key, Object set) {
        Object value = config.get(key);
        if (value == null) {
            config.set(key, (String) set);
            return config.get(key).asObject();
        }
        return value;
    }
}
