package io.mucahit.coderetreat.gol.noloopnoconditionals;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author mucahitkurt
 * @since 11.07.2018
 */
public class Habitat {

    private final Set<Point> members = new HashSet<>();

    private final Map<Boolean, BiFunction<Point, Integer, Habitat>> getNeigboursForAPointFunctions = new HashMap<>();

    public Habitat() {
        getNeigboursForAPointFunctions.put(true, returnHabitatFunction);
        getNeigboursForAPointFunctions.put(false, addPointToHabitatAndTriggerLookingForOtherPointsFunction);
    }

    public void place(Point point) {
        members.add(point);
    }

    public int getMembersCount() {
        return members.size();
    }


    public Habitat getMemberWithNeighbours(Point point) {

        final Habitat subHabitatWithNeighbours = new Habitat();
        subHabitatWithNeighbours.placeAll(getNeighboursForAPointFunction.apply(new Point(point.x - 1, point.y - 1), -1).getMembers());
        subHabitatWithNeighbours.placeAll(getNeighboursForAPointFunction.apply(new Point(point.x - 1, point.y), -1).getMembers());
        subHabitatWithNeighbours.placeAll(getNeighboursForAPointFunction.apply(new Point(point.x - 1, point.y + 1), -1).getMembers());

        return subHabitatWithNeighbours;
    }

    private BiFunction<Point, Integer, Habitat> getNeighboursForAPointFunction = (point, x) -> {
        Habitat habitat = new Habitat();
        habitat.placeAll(getNeigboursForAPointFunctions.get(x > 1).apply(point, x).getMembers());
        return habitat;
    };

    private BiFunction<Point, Integer, Habitat> addPointToHabitatAndTriggerLookingForOtherPointsFunction = (point, x) -> {
        final Habitat habitat = new Habitat();
        habitat.place(point);
        habitat.placeAll(getNeighboursForAPointFunction.apply(new Point(point.x + 1, point.y), ++x).getMembers());
        return habitat;
    };

    private BiFunction<Point, Integer, Habitat> returnHabitatFunction = (point, x) -> new Habitat();

    private void placeAll(Set<Point> members) {
        this.members.addAll(members);
    }

    public Set<Point> getMembers() {
        return members;
    }

    public HabitatPoint livenessAwarePoint(final Point point) {

        final HabitatPoint habitatPoint = new LivenessCheck().livenessAwarePoint(this.members.contains(point), point);
        habitatPoint.aliveNeighbours = countAliveNeighbours(new ArrayList<>(getMemberWithNeighbours(point).getMembers()));

        return habitatPoint;
    }

    private int countAliveNeighbours(List<Point> members) {
        final Point point;
        try {
            point = members.remove(0);
        } catch (Exception e) {
            return 0;
        }
        return new LivenessCheck().livenessAwarePoint(this.members.contains(point), point).livenessConstant() + countAliveNeighbours(members);
    }

    public void trim() {
        this.members.remove(null);
    }
}