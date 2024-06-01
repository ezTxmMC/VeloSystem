package eu.moonwriters.velosystemcore.config

import dev.eztxm.config.JsonConfig
import eu.moonwriters.velosystemcore.util.ValueType
import lombok.Getter

@Getter
class MotdConfig(private val config: JsonConfig) {
    @Getter
    private var lineOne: List<String>

    @Getter
    private var lineTwo: List<String>

    @Getter
    private var maintenanceLineOne: List<String>

    @Getter
    private var maintenanceLineTwo: List<String>

    @Getter
    private var maintenanceScreen: List<String>

    @Getter
    private var maintenance: Boolean


    init {
        this.lineOne = getOrSet(
            ValueType.LIST,
            "Line-1",
            listOf("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]")
        ) as List<String>
        this.lineTwo = getOrSet(ValueType.LIST, "Line-2", listOf("&c&lNEW! &b&lCITYBUILD UPDATE")) as List<String>
        this.maintenanceLineOne = getOrSet(
            ValueType.LIST,
            "Maintenance-Line-1",
            listOf("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]")
        ) as List<String>
        this.maintenanceLineTwo = getOrSet(
            ValueType.LIST,
            "Maintenance-Line-2",
            listOf("&c&lMAINTENANCE! &7We are in maintenance for the next major update")
        ) as List<String>
        this.maintenanceScreen = getOrSet(
            ValueType.LIST,
            "Maintenance-Screen",
            listOf("&e&lYourServer.com", " ", "&7We are in &cmaintenance", "&7Check our twitter and discord for news!")
        ) as List<String>
        this.maintenance = getOrSet(ValueType.BOOLEAN, "Maintenance", true) as Boolean
    }

    fun update() {
        this.lineOne = getOrSet(
            ValueType.LIST,
            "Line-1",
            listOf("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]")
        ) as List<String>
        this.lineTwo = getOrSet(ValueType.LIST, "Line-2", listOf("&c&lNEW! &b&lCITYBUILD UPDATE")) as List<String>
        this.maintenanceLineOne = getOrSet(
            ValueType.LIST,
            "Maintenance-Line-1",
            listOf("&e&lYourServer.com &8- &7The one of the all &8[&c1.20.4&8]")
        ) as List<String>
        this.maintenanceLineTwo = getOrSet(
            ValueType.LIST,
            "Maintenance-Line-2",
            listOf("&c&lMAINTENANCE! &7We are in maintenance for the next major update")
        ) as List<String>
        this.maintenanceScreen = getOrSet(
            ValueType.LIST,
            "Maintenance-Screen",
            listOf("&e&lYourServer.com", " ", "&7We are in &cmaintenance", "&7Check our twitter and discord for news!")
        ) as List<String>
        this.maintenance = getOrSet(ValueType.BOOLEAN, "Maintenance", true) as Boolean
    }

    private fun getOrSet(type: ValueType, key: String, set: Any): Any {
        val value: Any? = config[key]
        if (value == null) {
            config[key] = set as String
            return config[key].asObject()
        }
        return value
    }
}
