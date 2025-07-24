package com.lightning.northstar.planet.orbit;

import com.lightning.northstar.Northstar;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3d;

public record PhysicalOrbitProvider(
        double gravitationalParameter,
        double semiMajorAxis,
        double eccentricity,
        double inclinationDeg,
        double longitudeOfAscendingNodeDeg,
        double argumentOfPeriapsisDeg,
        double meanAnomalyDeg
) implements OrbitProvider {

    public static final ResourceLocation NAME = Northstar.asResource("physical");
    public static final Codec<PhysicalOrbitProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("gravitational_parameter").forGetter(PhysicalOrbitProvider::gravitationalParameter),
            Codec.DOUBLE.fieldOf("semi_major_axis").forGetter(PhysicalOrbitProvider::semiMajorAxis),
            Codec.DOUBLE.fieldOf("eccentricity").forGetter(PhysicalOrbitProvider::eccentricity),
            Codec.DOUBLE.fieldOf("inclination_deg").forGetter(PhysicalOrbitProvider::inclinationDeg),
            Codec.DOUBLE.fieldOf("longitude_of_ascending_node_deg").forGetter(PhysicalOrbitProvider::longitudeOfAscendingNodeDeg),
            Codec.DOUBLE.fieldOf("argument_of_periapsis_deg").forGetter(PhysicalOrbitProvider::argumentOfPeriapsisDeg),
            Codec.DOUBLE.fieldOf("mean_anomaly_deg").forGetter(PhysicalOrbitProvider::meanAnomalyDeg)
    ).apply(instance, PhysicalOrbitProvider::new));

    @Override
    public ResourceLocation codec() {
        return NAME;
    }

    @Override
    public double approximateRadius() {
        return semiMajorAxis;
    }

    @Override
    public Vector3d calculatePosition(double deltaDays, Vector3d dest) {
        if (semiMajorAxis <= 0.0000001) {
            return dest.set(0);
        }

        double meanAnomalyRad = Math.toRadians(meanAnomalyDeg);
        double longitudeOfAscendingNode = Math.toRadians(longitudeOfAscendingNodeDeg);
        double argumentOfPeriapsis = Math.toRadians(argumentOfPeriapsisDeg);
        double inclination = Math.toRadians(inclinationDeg);

        //double gravitationalParameter = 0.00029591220828559104; // AU^3/day^2

        // Mean motion (rad/day)
        double meanMotion = Math.sqrt(gravitationalParameter / Math.pow(semiMajorAxis, 3));

        // Mean anomaly at time t
        double meanAnomaly = (meanAnomalyRad + meanMotion * deltaDays) % (Math.PI * 2);
        if (meanAnomaly < 0) meanAnomaly += Math.PI * 2;

        // Solve Kepler's equation for eccentric anomaly
        double eccentricAnomaly = kepler(meanAnomaly, eccentricity);

        // Compute true anomaly
        double trueAnomaly = 2 * Math.atan2(
                Math.sqrt(1 + eccentricity) * Math.sin(eccentricAnomaly / 2),
                Math.sqrt(1 - eccentricity) * Math.cos(eccentricAnomaly / 2)
        );

        // Distance from focus
        double distance = semiMajorAxis * (1 - eccentricity * Math.cos(eccentricAnomaly));

        double cos_Omega = Math.cos(longitudeOfAscendingNode);
        double sin_Omega = Math.sin(longitudeOfAscendingNode);
        double cos_arg_plus_true = Math.cos(argumentOfPeriapsis + trueAnomaly);
        double sin_arg_plus_true = Math.sin(argumentOfPeriapsis + trueAnomaly);
        double cos_incl = Math.cos(inclination);
        double sin_incl = Math.sin(inclination);

        // Rotate to ecliptic coordinates
        return dest.set(
                distance * (cos_Omega * cos_arg_plus_true - sin_Omega * sin_arg_plus_true * cos_incl),
                distance * (sin_Omega * cos_arg_plus_true + cos_Omega * sin_arg_plus_true * cos_incl),
                distance * (sin_arg_plus_true * sin_incl)
        );
    }

    private double kepler(double M, double e) {
        double E = e < 0.8 ? M : Math.PI;
        while (true) {
            double delta = (E - e * Math.sin(E) - M) / (1 - e * Math.cos(E));
            E -= delta;
            if (Math.abs(delta) < 0.00001) {
                break;
            }
            if (Double.isNaN(E)) return E;
        }
        return E;
    }

}
