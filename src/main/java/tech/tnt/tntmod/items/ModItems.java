package tech.tnt.tntmod.items;

import net.minecraft.registry.Registry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import tech.tnt.tntmod.Tntmod;

public class ModItems {

public static final Item TNT_LAUNCHER= registerItem("tnt_launcher",new Item(new Item.Settings()));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Tntmod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        Tntmod.LOGGER.info("Mod Items Registered for -" + Tntmod.MOD_ID);
    }
}
