package com.lightning.northstar.client.model.armor;

import com.lightning.northstar.content.NorthstarEntityResources;
import com.lightning.northstar.item.armor.SpaceSuitArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IronSpaceSuitArmorModel extends GeoModel<SpaceSuitArmorItem> {

    @Override
    public ResourceLocation getAnimationResource(SpaceSuitArmorItem animatable) {
        return NorthstarEntityResources.IRON_SPACESUIT_ARMOR_ANIMATIONS;
    }

    @Override
    public ResourceLocation getModelResource(SpaceSuitArmorItem object) {
        return NorthstarEntityResources.IRON_SPACESUIT_ARMOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SpaceSuitArmorItem object) {
        return NorthstarEntityResources.IRON_SPACESUIT_ARMOR_TEXTURE;
    }

}
