package eu.moonwriters.velosystemspigot.inventory;

import eu.moonwriters.velosystemspigot.util.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class PartyInventory implements InventoryHolder {
    private final Inventory inventory;

    public PartyInventory() {
        inventory = Bukkit.createInventory(this, 9 * 5);
        InventoryHelper helper = new InventoryHelper(inventory);
        helper.setGlass(Material.BLACK_STAINED_GLASS_PANE, 9 * 5);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
