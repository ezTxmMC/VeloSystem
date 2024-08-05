package eu.moonwriters.velosystemspigot.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHelper {
    private final Inventory inventory;

    public InventoryHelper(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setGlass(Material material, int inventorySize) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§0");
        stack.setItemMeta(meta);
        for (int i = 0; i < inventorySize; i++) {
            inventory.setItem(i, stack);
        }
        for (int i = 9; i < inventorySize - 10; i++) {
            inventory.setItem(i, new ItemStack(Material.AIR));
        }
        switch (inventorySize) {
            case 9 * 4 -> {
                inventory.setItem(18, stack);
                inventory.setItem(26, stack);
            }
            case 9 * 5 -> {
                inventory.setItem(18, stack);
                inventory.setItem(26, stack);
                inventory.setItem(27, stack);
                inventory.setItem(35, stack);
            }
            case 9 * 6 -> {
                inventory.setItem(18, stack);
                inventory.setItem(26, stack);
                inventory.setItem(27, stack);
                inventory.setItem(35, stack);
                inventory.setItem(36, stack);
                inventory.setItem(44, stack);
            }
        }
    }
}
