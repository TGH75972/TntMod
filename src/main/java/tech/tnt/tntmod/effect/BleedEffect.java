package tech.tnt.tntmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import org.joml.Vector3f;
import tech.tnt.tntmod.sound.ModSounds;
public class BleedEffect extends StatusEffect {
public BleedEffect(StatusEffectCategory statusEffectCategory, int color) {
super(statusEffectCategory, color);
}
@Override
public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
if (!entity.getWorld().isClient && entity.getWorld() instanceof ServerWorld serverWorld) {
DamageSource bleedDamage = new DamageSource(serverWorld.getRegistryManager().getWrapperOrThrow(net.minecraft.registry.RegistryKeys.DAMAGE_TYPE).getOrThrow(DamageTypes.GENERIC_KILL));
entity.damage(bleedDamage, 1.0F);
for (int i = 0; i < 5; i++) {
double x = entity.getParticleX(0.5);
double y = entity.getRandomBodyY();
double z = entity.getParticleZ(0.5);
serverWorld.spawnParticles(new DustParticleEffect(new Vector3f(0.5f, 0.0f, 0.0f), 2.0f),x, y, z, 1, 0.1, 0.1, 0.1, 0.0);
}

if (entity instanceof PlayerEntity player && player.getHealth() <= 12.0F) {
player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 30, 0, false, false));

if (player instanceof ServerPlayerEntity serverPlayer) {
serverPlayer.getWorld().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), ModSounds.HEARTBEAT, SoundCategory.PLAYERS, 1.0F, 1.0F);
  }
 }
}
return true;
}

@Override
public boolean canApplyUpdateEffect(int duration, int amplifier) {
return duration % 10 == 0;
}
}