package vswe.stevescarts.old.Modules.Addons.Mobdetectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayerMP;
import vswe.stevescarts.vehicles.entities.EntityModularCart;
import vswe.stevescarts.old.Helpers.Localization;

public class ModulePlayer extends ModuleMobdetector {
	public ModulePlayer(EntityModularCart cart) {
		super(cart);
	}

	public String getName() {
		return Localization.MODULES.ADDONS.DETECTOR_PLAYERS.translate();
	}
	public boolean isValidTarget(Entity target) {
		return
		(
			target instanceof EntityPlayerMP
			||
			(
				(target instanceof EntityTameable)
				&&
				((EntityTameable)target).isTamed()
			)			
		)
		;
	}
}