package io.mucahit.coderetreat.gol.noloop;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mucahitkurt
 * @since 11.07.2018
 */
public class Habitat {

    private final Set<Point> members = new HashSet<>();

    public void place(Point point) {
        members.add(point);
    }

    public int getMembersCount() {
        return members.size();
    }

    public Habitat getMemberWithNeighbours(Point point) {

        final Habitat subHabitatWithNeighbours = new Habitat();
        subHabitatWithNeighbours.placeAll(getNeighboursForRow(new Point(point.x - 1, point.y - 1), -1).getMembers());
        subHabitatWithNeighbours.placeAll(getNeighboursForRow(new Point(point.x - 1, point.y), -1).getMembers());
        subHabitatWithNeighbours.placeAll(getNeighboursForRow(new Point(point.x - 1, point.y + 1), -1).getMembers());

        return subHabitatWithNeighbours;
    }

    private Habitat getNeighboursForRow(Point point, int x) {

        final Habitat habitat = new Habitat();
        if (x > 1) {
            return habitat;
        }

        habitat.place(point);
        habitat.placeAll(getNeighboursForRow(new Point(point.x + 1, point.y), ++x).getMembers());
        return habitat;
    }

    private void placeAll(Set<Point> members) {
        this.members.addAll(members);
    }

    public Set<Point> getMembers() {
        return members;
    }

    public HabitatPoint livenessAwarePoint(final Point point) {

        final var aliveMemberCount = new AtomicInteger(0);

        getMemberWithNeighbours(point).getMembers().forEach(point1 -> {
            aliveMemberCount.addAndGet(new LivenessCheck().livenessAwarePoint(this.members.contains(point1), point1).livenessConstant());
        });

        final HabitatPoint habitatPoint = new LivenessCheck().livenessAwarePoint(this.members.contains(point), point);
        habitatPoint.aliveNeighbours = aliveMemberCount.get();

        return habitatPoint;
    }

    public void trim() {
        this.members.remove(null);
    }
}