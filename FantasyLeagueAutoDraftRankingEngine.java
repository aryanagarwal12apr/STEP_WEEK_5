import java.util.Arrays;

class Player implements Comparable<Player> {

    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;

  public Player(String name, int matchesPlayed,
                  double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

  static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    @Override
    public int compareTo(Player other) {
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable = new Player[players.length];
        int count = 0;

        for (int i = 0; i < players.length; i++) {

            boolean eligible;

            if (players[i].matchesPlayed >= 10) {
                eligible = isDraftable(players[i].matchesPlayed);
            } else {
                eligible = isDraftable(
                    players[i].matchesPlayed,
                    players[i].injured
                ) && players[i].battingAverage >= 50;
            }

            if (eligible) {
                draftable[count] = players[i];
                count++;
            }
        }

        Player[] result = Arrays.copyOf(draftable, count);

        Arrays.sort(result);

        String output = "";

        for (int i = 0; i < result.length; i++) {
            output = output + (i + 1) + ". " + result[i].name;

            if (i < result.length - 1) {
                output = output + " | ";
            }
        }

        return output;
    }

    public static void main(String[] args) {

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}
