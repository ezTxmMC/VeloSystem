package eu.moonwriters.velosystemspigot

import org.bukkit.plugin.java.JavaPlugin

class VeloSystem : JavaPlugin() {
    override fun onEnable() {
        instance = this
    }

    override fun onDisable() {
        instance = null
    }

    companion object {
        var instance: VeloSystem? = null
            private set
    }
}
