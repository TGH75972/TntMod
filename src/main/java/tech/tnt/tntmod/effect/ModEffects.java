package tech.tnt.tntmod.effect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import tech.tnt.tntmod.Tntmod;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> BLEED = registerStatusEffect("bleed", new BleedEffect(StatusEffectCategory.HARMFUL, 0x8B0000));

private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
    return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Tntmod.MOD_ID, name), statusEffect);
}
public static void registerModEffects() {
    Tntmod.LOGGER.info("Registering Mod Effects for " + Tntmod.MOD_ID);
}
}
