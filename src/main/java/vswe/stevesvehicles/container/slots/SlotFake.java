package vswe.stevesvehicles.container.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import vswe.stevesvehicles.transfer.TransferHandler;

public abstract class SlotFake extends SlotBase implements ISpecialItemTransferValidator {
	public SlotFake(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}

	@Override
	public int getSlotStackLimit() {
		return 0;
	}

	@Override
	public ItemStack func_190901_a(EntityPlayer player, ItemStack item) {
		if (item != null && player != null && player.inventory != null) {
			player.inventory.setItemStack(null);
		}
		return super.func_190901_a(player, item);
	}

	@Override
	public boolean isItemValidForTransfer(ItemStack item, TransferHandler.TransferType type) {
		return false;
	}
}
