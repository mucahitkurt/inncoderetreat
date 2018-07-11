package io.mucahit.coderetreat.gol.noconditional;

import java.awt.*;
import java.util.HashSet;
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

        Habitat subHabitatWithNeighbours = new Habitat();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                subHabitatWithNeighbours.place(new Point(point.x + x, point.y + y));
            }
        }

        return subHabitatWithNeighbours;
    }

    public Set<Point> getMembers() {
        return members;
    }

    public HabitatPoint livenessAwarePoint(final Point point) {

        final Habitat memberWithNeigbours = getMemberWithNeighbours(point);
        var aliveMemberCount = 0;

        for (Point point1 : memberWithNeigbours.getMembers()) {
            aliveMemberCount += new LivenessCheck().livenessAwarePoint(this.members.contains(point1), point1).livenessConstant();
        }

        final HabitatPoint habitatPoint = new LivenessCheck().livenessAwarePoint(this.members.contains(point), point);
        habitatPoint.aliveNeighbours = aliveMemberCount;

        return habitatPoint;
    }

    public void trim() {
        this.members.remove(null);
    }
}
