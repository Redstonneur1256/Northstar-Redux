package com.lightning.northstar.planet;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum PlanetSprite implements StringRepresentable {

    VERY_CLOSE,
    CLOSE,
    FAR;

    public static final Codec<PlanetSprite> CODEC = StringRepresentable.fromEnum(PlanetSprite::values);

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }

}
