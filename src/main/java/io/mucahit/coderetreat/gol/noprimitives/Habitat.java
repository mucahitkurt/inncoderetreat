package io.mucahit.coderetreat.gol.noprimitives;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mkurt
 * @since 11.07.2018 12:09
 */
public class Habitat {

    private Set<Point> members = new HashSet<>();


    public void place(Point point) {
        members.add(point);
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
