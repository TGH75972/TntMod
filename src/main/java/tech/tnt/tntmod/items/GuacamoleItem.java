package tech.tnt.tntmod.items;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import tech.tnt.tntmod.Tntmod;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class GuacamoleItem extends Item {
public static final Identifier SHRINK_ID = Identifier.of(Tntmod.MOD_ID, "guacamole_shrink");
private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
public GuacamoleItem(Settings settings) {
super(settings);
}
@Override
public UseAction getUseAction(ItemStack stack) {
return UseAction.EAT;
}
@Override
public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
ItemStack resultStack = super.finishUsing(stack, world, user);
if (user instanceof PlayerEntity player) {
if (!world.isClient) {
EntityAttributeInstance attr = player.getAttributeInstance(EntityAttributes.GENERIC_SCALE);
if (attr != null && !attr.hasModifier(SHRINK_ID)) {
player.sendMessage(Text.literal("enjoy being a mole :)"), true);
EntityAttributeModifier modifier = new EntityAttributeModifier(SHRINK_ID, -0.45, EntityAttributeModifier.Operation.ADD_VALUE);
attr.addPersistentModifier(modifier);
scheduler.schedule(() -> {
if (world instanceof ServerWorld serverWorld) {
serverWorld.getServer().execute(() -> {
if (player.isAlive()) {
EntityAttributeInstance currentAttr = player.getAttributeInstance(EntityAttributes.GENERIC_SCALE);
if (currentAttr != null && currentAttr.hasModifier(SHRINK_ID)) {
currentAttr.removeModifier(SHRINK_ID);
}
}
});
}
}, 30, TimeUnit.SECONDS);
}
}
if (stack.isEmpty()) {
return new ItemStack(Items.BOWL);
}
player.getInventory().insertStack(new ItemStack(Items.BOWL));
}
return resultStack;
}
}