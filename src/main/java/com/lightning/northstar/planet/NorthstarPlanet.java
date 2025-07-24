package com.lightning.northstar.planet;

import com.lightning.northstar.content.NorthstarRegistries;
import com.lightning.northstar.planet.orbit.OrbitProvider;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.Map;
import java.util.Optional;

// TODO: replace rotationPeriodDay by a dayLengthMultiplier?

/**
 * @param type               the type key, planet, star, gas_giant, etc...
 * @param gravity            the gravity in m/s^2
 * @param hasAtmosphere      wether the planet has a breathable atmosphere or requires the use of a space suit
 * @param temperature        the surface temperature in Celsius
 * @param rotationPeriodDays the amount of days it takes for the planet to complete a full cycle on itself
 * @param circumference      the planet circumference, in kilometers.
 * @param orbit              the planet's orbit
 * @param textures           the different textures to be used in the sky and telescope
 * @param orreryModel        the planet model for the orrery
 */
public record NorthstarPlanet(
        ResourceKey<DimensionType> dimension,
        ResourceKey<NorthstarPlanet> centralBody,

        String type,
        double gravity,
        boolean hasAtmosphere,
        double temperature,

        double rotationPeriodDays,
        double circumference,
        OrbitProvider orbit,

        Map<PlanetSprite, ResourceLocation> textures,
        ResourceLocation orreryModel
) {

    // TODO: Jupiter, Saturn, Uranus, and Neptune (not landable but still cool)

    public static final Codec<NorthstarPlanet> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceKey.codec(Registries.DIMENSION_TYPE).optionalFieldOf("dimension").forGetter(planet -> Optional.ofNullable(planet.dimension())),
            ResourceKey.codec(NorthstarRegistries.PLANETS).optionalFieldOf("central_body").forGetter(planet -> Optional.ofNullable(planet.centralBody())),

            Codec.STRING.fieldOf("type").forGetter(NorthstarPlanet::type),
            Codec.DOUBLE.fieldOf("gravity").forGetter(NorthstarPlanet::gravity),
            Codec.BOOL.fieldOf("atmosphere").forGetter(NorthstarPlanet::hasAtmosphere),
            Codec.DOUBLE.fieldOf("temperature").forGetter(NorthstarPlanet::temperature),

            Codec.DOUBLE.optionalFieldOf("rotation_period_days", 1.0).forGetter(NorthstarPlanet::rotationPeriodDays),
            Codec.DOUBLE.optionalFieldOf("circumference", 40000.0).forGetter(NorthstarPlanet::circumference),

            OrbitProvider.CODEC.fieldOf("orbit").forGetter(NorthstarPlanet::orbit),

            Codec.unboundedMap(PlanetSprite.CODEC, ResourceLocation.CODEC).fieldOf("textures").forGetter(NorthstarPlanet::textures),
            ResourceLocation.CODEC.fieldOf("orrery_model").forGetter(NorthstarPlanet::orreryModel)
    ).apply(instance, (dimension, centralBody, type, gravity, hasAtmosphere, temperature, rotationPeriodDays, circumference, orbit, textures, orreryModel) ->
            new NorthstarPlanet(dimension.orElse(null), centralBody.orElse(null), type, gravity, hasAtmosphere, temperature, rotationPeriodDays, circumference, orbit, textures, orreryModel)));

    public float gravityScale() {
        return (float) gravity / 9.807f;
    }

}
