package tech.tnt.tntmod.sound;
import net.minecraft.sound.SoundEvent;
import tech.tnt.tntmod.Tntmod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
public class ModSounds {
public static final SoundEvent TNT_LAUNCHER_SHOOT = registerSoundEvent("tnt_launcher_shoot"); 
public static final SoundEvent TNT_LAUNCHER_RELOAD = registerSoundEvent("tnt_launcher_reload");
public static final SoundEvent TNT_LAUNCHER_NOAMMO = registerSoundEvent("tnt_launcher_noammo");
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Tntmod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerModSounds() {
      Tntmod.LOGGER.info("Mod Sounds Registered for -" + Tntmod.MOD_ID);
    }
}
