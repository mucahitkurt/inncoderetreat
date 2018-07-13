package io.mucahit.coderetreat.gol.telldontask;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mkurt
 * @since 13.07.2018 12:28
 */
public class Board {

    public void advance(String pattern, Set<String> nextGeneration) {

        Set<String> livingMembers = new HashSet<>();
        generateLivingMembers(livingMembers, pattern);

        Set<String> livingMemberWithNeigbours = new HashSet<>();
        livingMembers.forEach(livingMember -> livingMemberWithNeighbours(livingMemberWithNeigbours, livingMember));

        livingMemberWithNeigbours.forEach(member -> nextGeneration(nextGeneration, livingMembers, member));
    }

    private void nextGeneration(Set<String> nextGeneration, Set<String> livingMembers, String member) {

        Set<String> livingMembersWithNeighbours = new HashSet<>();
        livingMemberWithNeighbours(livingMembersWithNeighbours, member);
        var liveCount = 0;
        for (String memberFromlivingMembersWithNeighbour : livingMembersWithNeighbours) {
            if (livingMembers.contains(memberFromlivingMembersWithNeighbour)) {
                liveCount++;
            }
        }

        if (liveCount == 3) {
            nextGeneration.add(member);
        } else if (liveCount == 4 && livingMembers.contains(member)) {
            nextGeneration.add(member);
        }

    }

    private void livingMemberWithNeighbours(Set<String> livingMemberWithNeigbours, String livingMember) {

        int livingX = Integer.parseInt(livingMember.split("~")[0]);
        int livingY = Integer.parseInt(livingMember.split("~")[1]);

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                livingMemberWithNeigbours.add(String.valueOf(x + livingX) + "~" + String.valueOf(y + livingY));
            }
        }
    }

    private void generateLivingMembers(Set<String> livingMembers, String pattern) {

        var x = 0;
        var y = 0;
        for (char c : pattern.toCharArray()) {
            if (c == '\n') {
                x = 0;
                y++;
            } else if (c == '*') {
                livingMembers.add(x + "~" + y);
                x++;
            } else {
                x++;
            }
        }
    }
}
