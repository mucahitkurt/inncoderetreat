package io.mucahit.coderetreat.gol.noloop;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
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

        Habitat subHabitatWithNeighbours = new Habitat();

        List.of(-1, 0, 1).forEach(x ->
                List.of(-1, 0, 1).forEach(y ->
                        subHabitatWithNeighbours.place(new Point(point.x + x, point.y + y))));

        return subHabitatWithNeighbours;
    }

    public Set<Point> getMembers() {
        return members;
    }

    public HabitatPoint livenessAwarePoint(final Point point) {

        final Habitat memberWithNeigbours = getMemberWithNeighbours(point);
        final var aliveMemberCount = new AtomicInteger(0);

        memberWithNeigbours.getMembers().forEach(point1 -> {
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
