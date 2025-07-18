package com.lightning.northstar.client.model.armor;

import com.lightning.northstar.content.NorthstarEntityResources;
import com.lightning.northstar.item.armor.BrokenIronSpaceSuitArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BrokenIronSpaceSuitArmorModel extends GeoModel<BrokenIronSpaceSuitArmorItem> {

    @Override
    public ResourceLocation getAnimationResource(BrokenIronSpaceSuitArmorItem animatable) {
        return NorthstarEntityResources.BROKEN_IRON_SPACESUIT_ARMOR_ANIMATIONS;
    }

    @Override
    public ResourceLocation getModelResource(BrokenIronSpaceSuitArmorItem object) {
        return NorthstarEntityResources.BROKEN_IRON_SPACESUIT_ARMOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(BrokenIronSpaceSuitArmorItem object) {
        return NorthstarEntityResources.BROKEN_IRON_SPACESUIT_ARMOR_TEXTURE;
    }

}
