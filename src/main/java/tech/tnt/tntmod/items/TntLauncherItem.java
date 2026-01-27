package tech.tnt.tntmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TntLauncherItem extends Item {

public TntLauncherItem(Settings settings) {
super(settings);
}

@Override
public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
user.setCurrentHand(hand);
return TypedActionResult.consume(user.getStackInHand(hand));
}

@Override
public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
if (world.isClient)
return;
if (!(user instanceof PlayerEntity player)) 
return;

int usedTicks = 72000 - remainingUseTicks;
if (usedTicks < 10) 
return;

if(!player.getInventory().contains(new ItemStack(net.minecraft.item.Items.TNT))) 
return;

player.getInventory().remove(stack2 -> stack2.isOf(Items.TNT), 1, player.playerScreenHandler.getCraftingInput());

Vec3d look = player.getRotationVec(1.0F);
Vec3d spawnPos = player.getEyePos().add(look.multiply(1.5));

TntEntity tnt = new TntEntity(world, spawnPos.x, spawnPos.y, spawnPos.z, player);
tnt.setFuse(6);
tnt.setVelocity(look.multiply(1.5));

world.spawnEntity(tnt);
  if (world.isClient) {
    for (int i = 0; i < 8; i++) {
    world.addParticle(
    net.minecraft.particle.ParticleTypes.SMOKE,spawnPos.x,spawnPos.y,spawnPos.z,look.x * 0.1,look.y * 0.1,look.z * 0.1);
    }
return;
    }
 }
}
