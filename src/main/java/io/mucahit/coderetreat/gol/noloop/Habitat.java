package io.mucahit.coderetreat.gol.noloop;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        final var aliveMemberCount = countAliveNeighbours(new ArrayList<>(getMemberWithNeighbours(point).getMembers()));

        final HabitatPoint habitatPoint = new LivenessCheck().livenessAwarePoint(this.members.contains(point), point);
        habitatPoint.aliveNeighbours = aliveMemberCount;

        return habitatPoint;
    }

    private int countAliveNeighbours(List<Point> members) {
        if (members.isEmpty()) {
            return 0;
        }

        final Point point = members.remove(0);
        return new LivenessCheck().livenessAwarePoint(this.members.contains(point), point).livenessConstant() + countAliveNeighbours(members);
    }

    public void trim() {
        this.members.remove(null);
    }
}