package tech.tnt.tntmod.items;

import net.minecraft.registry.Registry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import tech.tnt.tntmod.Tntmod;           
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class ModItems { 

public static final Item TNT_LAUNCHER= registerItem("tnt_launcher",new TntLauncherItem(new Item.Settings().maxCount(1).maxDamage(200)));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Tntmod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        Tntmod.LOGGER.info("Mod Items Registered for -" + Tntmod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(TNT_LAUNCHER));
        Registry.register(Registries.ITEM, Identifier.of(Tntmod.MOD_ID, "guacamole"), GUACAMOLE);
    }

    public static final Item GUACAMOLE = new GuacamoleItem(new Item.Settings().food(ModFood.GUACAMOLE).maxCount(1));
    

}
