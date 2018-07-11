package io.mucahit.coderetreat.gol.noloop;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author mucahitkurt
 * @since 12.07.2018
 */
public class LivenessCheck {

    private Map<Boolean, Function> liveOrDeadConstant = new HashMap<>();

    private Function<Point, HabitatPoint> createDeadPoint = (DeadPoint::new);
    private Function<Point, HabitatPoint> createAlivePoint = (AlivePoint::new);

    public LivenessCheck() {
        liveOrDeadConstant.put(true, createAlivePoint);
        liveOrDeadConstant.put(false, createDeadPoint);
    }

    public HabitatPoint livenessAwarePoint(boolean isAlive, Point point) {
        return (HabitatPoint) liveOrDeadConstant.get(isAlive).apply(point);
    }


}
