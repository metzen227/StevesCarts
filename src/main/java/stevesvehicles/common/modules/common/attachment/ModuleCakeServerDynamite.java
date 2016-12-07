package stevesvehicles.common.modules.common.attachment;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import stevesvehicles.common.container.slots.SlotBase;
import stevesvehicles.common.container.slots.SlotCakeDynamite;
import stevesvehicles.common.core.Config;
import stevesvehicles.common.items.ModItems;
import stevesvehicles.common.vehicles.VehicleBase;

public class ModuleCakeServerDynamite extends ModuleCakeServer {
	private int dynamiteCount;

	private int getMaxDynamiteCount() {
		return Math.min(Config.maxDynamites, 25);
	}

	public ModuleCakeServerDynamite(VehicleBase vehicleBase) {
		super(vehicleBase);
	}

	@Override
	protected SlotBase getSlot(int slotId, int x, int y) {
		return new SlotCakeDynamite(getVehicle().getVehicleEntity(), slotId, 8 + x * 18, 38 + y * 18);
	}

	@Override
	public boolean dropOnDeath() {
		return dynamiteCount == 0;
	}

	@Override
	public void onDeath() {
		if (dynamiteCount > 0) {
			explode();
		}
	}

	private void explode() {
		getVehicle().getWorld().createExplosion(null, getVehicle().getEntity().posX, getVehicle().getEntity().posY, getVehicle().getEntity().posZ, dynamiteCount * 0.8F, true);
	}

	@Override
	public void update() {
		super.update();
		if (!getVehicle().getWorld().isRemote) {
			ItemStack item = getStack(0);
			if (item != null && item.getItem().equals(ModItems.component) && item.getItemDamage() == 6 && dynamiteCount < getMaxDynamiteCount()) {
				int count = Math.min(getMaxDynamiteCount() - dynamiteCount, item.getCount());
				dynamiteCount += count;
				item.shrink(count);
				if (item.getCount() == 0) {
					setStack(0, null);
				}
			}
		}
	}

	@Override
	public boolean onInteractFirst(EntityPlayer entityplayer) {
		if (dynamiteCount > 0) {
			explode();
			getVehicle().getEntity().setDead();
			return true;
		} else {
			return super.onInteractFirst(entityplayer);
		}
	}
}