package io.mucahit.coderetreat.gol.noloop;


/**
 * @author mucahitkurt
 * @since 11.07.2018
 */
public class Board {
    private Habitat habitat;

    public Board(Habitat habitat) {
        this.habitat = habitat;
    }

    public void advance() {

        Habitat nextGeneration = new Habitat();

        habitat.getMembers().forEach(point ->
                habitat.getMemberWithNeighbours(point).getMembers().forEach(point1 -> {
                    final HabitatPoint habitatPoint = habitat.livenessAwarePoint(point1);
                    nextGeneration.place(habitatPoint.nextGeneration());
                }));

        nextGeneration.trim();
        this.habitat = nextGeneration;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}
