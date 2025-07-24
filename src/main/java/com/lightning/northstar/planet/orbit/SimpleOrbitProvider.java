package com.lightning.northstar.planet.orbit;

import com.lightning.northstar.Northstar;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3d;

/**
 * @param durationDays          how long does it take for the orbit a full circle around the central body
 * @param radius                the orbit radius in AU
 * @param inclinationDeg        the orbit inclination in degrees
 * @param ascendingNodeDeg      the ascending node angle
 * @param initialMeanAnomalyDeg the offset angle at the start of the world (base planets use J2000)
 * @see <a href="https://en.wikipedia.org/wiki/Orbital_elements#/media/File:Orbit1.svg">Orbital elements</a>
 * @see <a href="https://nssdc.gsfc.nasa.gov/planetary/planetfact.html">Source for the solar system.</a>
 */
public record SimpleOrbitProvider(
        double durationDays,
        double radius,
        double inclinationDeg,
        double ascendingNodeDeg,
        double initialMeanAnomalyDeg
) implements OrbitProvider {

    public static final ResourceLocation NAME = Northstar.asResource("simple");
    public static final Codec<SimpleOrbitProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("duration_days").forGetter(SimpleOrbitProvider::durationDays),
            Codec.DOUBLE.fieldOf("radius").forGetter(SimpleOrbitProvider::radius),
            Codec.doubleRange(-90, +90).optionalFieldOf("inclination_deg", 0.0).forGetter(SimpleOrbitProvider::inclinationDeg),
            Codec.DOUBLE.optionalFieldOf("ascending_node_deg", 0.0).forGetter(SimpleOrbitProvider::ascendingNodeDeg),
            Codec.DOUBLE.optionalFieldOf("initial_mean_anomaly_deg", 0.0).forGetter(SimpleOrbitProvider::initialMeanAnomalyDeg)
    ).apply(instance, SimpleOrbitProvider::new));

    @Override
    public ResourceLocation codec() {
        return NAME;
    }

    @Override
    public double approximateRadius() {
        return radius;
    }

    @Override
    public Vector3d calculatePosition(double deltaDays, Vector3d dest) {
        return dest.set(radius, 0, 0)
                .rotateY(Math.PI * 2 * deltaDays / durationDays + Math.toRadians(initialMeanAnomalyDeg - ascendingNodeDeg))
                .rotateX(Math.toRadians(inclinationDeg))
                .rotateY(Math.toRadians(ascendingNodeDeg));
    }

}
