package io.mucahit.coderetreat.gol.immutable;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mkurt
 * @since 11.07.2018 19:06
 */
public class Habitat {

    private final Set<Point> members;

    public Habitat(Set<Point> members) {
        this.members = new HashSet<>(members);
    }

    public Habitat(Set<Point> members, Point point) {
        Set<Point> newMembers = new HashSet<>(members);
        newMembers.add(point);
        this.members = new HashSet<>(newMembers);
    }

    public int getMembersCount() {
        return members.size();
    }

    public Habitat getMemberWithNeigbours(Point point) {
        Habitat memberWithNeighbours = new Habitat();

        for (Coordinate x = new Coordinate(-1); x.isBefore(new Coordinate(2)); x.forward()) {
            for (Coordinate y = new Coordinate(-1); y.isBefore(new Coordinate(2)); y.forward()) {
                memberWithNeighbours.place(new Point(point.x + x.value(), point.y + y.value()));
            }
        }

        return memberWithNeighbours;
    }

    private Habitat place(Point point) {
        return new Habitat(members, point);
    }

    public boolean isAlive(Point point) {
        return members.contains(point);
    }

    public Set<Point> getMembers() {
        return members;
    }

    public int livingMembersCount(Point point) {

        final Habitat memberWithNeigbours = getMemberWithNeigbours(point);
        final Habitat subHabitatForLivingCells = new Habitat();
        for (Point point1 : memberWithNeigbours.getMembers()) {
            if (isAlive(point1)) {
                subHabitatForLivingCells.place(point1);
            }
        }

        return subHabitatForLivingCells.getMembersCount();
    }
}