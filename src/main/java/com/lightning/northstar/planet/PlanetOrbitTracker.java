package com.lightning.northstar.planet;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.joml.Vector3d;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlanetOrbitTracker {

    private final Map<ResourceLocation, PlanetNode> dimensionToPlanet = new HashMap<>();
    private final Map<ResourceLocation, List<PlanetNode>> systems = new HashMap<>();
    private final Map<ResourceLocation, PlanetNode> planets = new HashMap<>();
    private final List<PlanetNode> roots = new ArrayList<>();
    private final List<PlanetNode> updateOrder = new ArrayList<>();
    private double deltaDays;

    public void updateOrbits(double days) {
        this.deltaDays = days;

        for (PlanetNode node : updateOrder) {
            NorthstarPlanet planet = node.planet;
            Vector3d position = planet.orbit().calculatePosition(days, node.position);

            Vector3d orreryPosition = node.orreryPosition.set(position);
            if (orreryPosition.lengthSquared() > 0.0000001) {
                orreryPosition.normalize().mul(node.orreryRadius * 0.2);
            }

            if (planet.centralBody() != null) {
                PlanetNode parent = planets.get(planet.centralBody().location());
                position.add(parent.position);
                orreryPosition.add(parent.orreryPosition);
            }
        }
    }

    public double getDeltaDays() {
        return deltaDays;
    }

    public PlanetNode getPlanet(Level level) {
        return dimensionToPlanet.get(level.dimensionTypeId().location());
    }

    public PlanetNode getPlanet(ResourceKey<DimensionType> key) {
        return dimensionToPlanet.get(key.location());
    }

    public PlanetNode getPlanet(ResourceLocation dimension) {
        return dimensionToPlanet.get(dimension);
    }

    public List<PlanetNode> getSystem(ResourceLocation location) {
        return systems.get(location);
    }

    public Map<ResourceLocation, PlanetNode> getPlanets() {
        return planets;
    }

    public List<PlanetNode> getRoots() {
        return roots;
    }

    public Vector3d getOrbitPosition(ResourceLocation location) {
        PlanetNode planet = planets.get(location);
        return planet == null ? null : planet.position;
    }

    public Vector3d getOrreryPosition(ResourceLocation location) {
        PlanetNode planet = planets.get(location);
        return planet == null ? null : planet.orreryPosition;
    }

    public void rebuildUpdateOrder(Registry<NorthstarPlanet> registry) {
        Map<ResourceLocation, PlanetNode> nodes = new HashMap<>();
        Function<ResourceLocation, PlanetNode> defaultNode = loc -> new PlanetNode(loc, registry.get(loc));

        for (ResourceLocation location : registry.keySet()) {
            PlanetNode node = nodes.computeIfAbsent(location, defaultNode);
            NorthstarPlanet planet = node.planet;

            if (planet.centralBody() != null) {
                if (registry.get(planet.centralBody()) == null) {
                    throw new IllegalStateException("Planet '" + location + "' is trying to reference centralBody '" + planet.centralBody() + "' which doesn't exist.");
                }

                nodes.computeIfAbsent(planet.centralBody().location(), defaultNode).moons.add(node);
            }
        }

        for (PlanetNode node : nodes.values()) {
            node.postBuild();

            Set<ResourceLocation> seen = new LinkedHashSet<>();
            node.walkPreOrder(p -> {
                if (!seen.add(p.key)) {
                    throw new IllegalStateException("Planets have circular reference: " + seen);
                }
            });
        }

        planets.clear();
        planets.putAll(nodes);

        dimensionToPlanet.clear();
        for (PlanetNode planet : planets.values()) {
            if (planet.planet.dimension() != null)
                dimensionToPlanet.put(planet.planet.dimension().location(), planet);
        }

        // TODO: merge the root planets/stars to a galaxy? Maybe add a galaxy property and have them be automatically generated
        roots.clear();
        roots.addAll(nodes
                .values()
                .stream()
                .filter(node -> node.planet.centralBody() == null)
                .toList());

        updateOrder.clear();
        systems.clear();
        for (PlanetNode root : roots) {
            List<PlanetNode> system = new ArrayList<>();
            root.walkPreOrder(system::add); // update the base and then the moons

            root.buildOrreryOrder(0);

            systems.put(root.key, system);
            updateOrder.addAll(system);
        }
    }

    public static class PlanetNode {

        public final ResourceLocation key;
        public final NorthstarPlanet planet;
        public final List<PlanetNode> moons;
        public final Vector3d position;
        public final Vector3d orreryPosition;
        private int orreryRadius;

        private PlanetNode(ResourceLocation key, NorthstarPlanet planet) {
            this.key = key;
            this.planet = planet;
            this.moons = new ArrayList<>();
            this.position = new Vector3d();
            this.orreryPosition = new Vector3d();
        }

        private void postBuild() {
            // ensures walking order consistency, also helps with building the orrery
            moons.sort(Comparator.comparingDouble(moon -> moon.planet.orbit().approximateRadius()));
        }

        public void walkPreOrder(Consumer<PlanetNode> consumer) {
            consumer.accept(this);
            for (PlanetNode moon : moons) {
                moon.walkPreOrder(consumer);
            }
        }

        public void walkPostOrder(Consumer<PlanetNode> consumer) {
            for (PlanetNode moon : moons) {
                moon.walkPostOrder(consumer);
            }
            consumer.accept(this);
        }

        private int buildOrreryOrder(int baseDistanceFromParent) {
            int totalRadius = 0;
            for (PlanetNode moon : moons) {
                totalRadius = moon.buildOrreryOrder(totalRadius);
            }

            baseDistanceFromParent += totalRadius + 1;
            orreryRadius = baseDistanceFromParent;
            baseDistanceFromParent += totalRadius;

            return baseDistanceFromParent;
        }

    }

}
