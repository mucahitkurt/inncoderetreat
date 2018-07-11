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

    public Habitat() {
        this.members = new HashSet<>();
    }

    public Habitat(final Set<Point> members, final Point point) {
        Set<Point> newMembers = new HashSet<>(members);
        newMembers.add(point);
        this.members = new HashSet<>(newMembers);
    }

    public int getMembersCount() {
        return members.size();
    }

    public Habitat getMemberWithNeigbours(final Point point) {

        Habitat memberWithNeighbours = new Habitat();

        for (final Coordinate x = new Coordinate(-1); x.isBefore(new Coordinate(2)); x.forward()) {
            for (final Coordinate y = new Coordinate(-1); y.isBefore(new Coordinate(2)); y.forward()) {
                memberWithNeighbours = memberWithNeighbours.place(new Point(point.x + x.value(), point.y + y.value()));
            }
        }

        return memberWithNeighbours;
    }

    public Habitat place(final Point point) {
        return new Habitat(members, point);
    }

    public boolean isAlive(final Point point) {
        return members.contains(point);
    }

    public Set<Point> getMembers() {
        return members;
    }

    public int livingMembersCount(final Point point) {

        final Habitat memberWithNeigbours = getMemberWithNeigbours(point);
        Habitat subHabitatForLivingCells = new Habitat();

        for (Point point1 : memberWithNeigbours.getMembers()) {
            if (isAlive(point1)) {
                subHabitatForLivingCells = subHabitatForLivingCells.place(point1);
            }
        }

        return subHabitatForLivingCells.getMembersCount();
    }
}