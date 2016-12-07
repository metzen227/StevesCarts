package stevesvehicles.common.blocks.tileentitys.distributor;

import stevesvehicles.api.modules.data.ILocalizedText;
import stevesvehicles.common.blocks.tileentitys.TileEntityManager;

/**
 * Created by Vswe on 15/07/14.
 */
class DistributorSettingDirection extends DistributorSetting {
	private boolean toCart;

	public DistributorSettingDirection(int id, boolean top, ILocalizedText name, boolean toCart) {
		super(id, top, name);
		this.toCart = toCart;
	}

	@Override
	public boolean isValid(TileEntityManager manager, int chunkId, boolean top) {
		if (manager.layoutType == 0) {
			return super.isValid(manager, chunkId, top);
		} else {
			return super.isValid(manager, chunkId, top) && manager.toCart[chunkId] == toCart;
		}
	}
}