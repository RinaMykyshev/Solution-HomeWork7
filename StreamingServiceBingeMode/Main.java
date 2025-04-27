public class Main {
    public static void main(String[] args) {
        Series series = new Series();

        Season season1 = new Season();
        season1.addEpisode(new Episode("S1E1: The Beginning", 1200));
        season1.addEpisode(new Episode("S1E2: The Plot Thickens", 1250));

        Season season2 = new Season();
        season2.addEpisode(new Episode("S2E1: The Return", 1300));
        season2.addEpisode(new Episode("S2E2: Finale", 1400));

        series.addSeason(season1);
        series.addSeason(season2);

        System.out.println("Normal Iterator (Season 1):");
        EpisodeIterator normalIt = season1.createIterator();
        while (normalIt.hasNext()) {
            System.out.println(normalIt.next());
        }

        System.out.println("\nReverse Iterator (Season 2):");
        EpisodeIterator revIt = season2.createReverseIterator();
        while (revIt.hasNext()) {
            System.out.println(revIt.next());
        }

        System.out.println("\nShuffle Iterator (Season 1, seed=42):");
        EpisodeIterator shuffleIt = season1.createShuffleIterator(42);
        while (shuffleIt.hasNext()) {
            System.out.println(shuffleIt.next());
        }

        System.out.println("\nBinge Iterator (Entire Series):");
        EpisodeIterator bingeIt = series.createBingeIterator();
        while (bingeIt.hasNext()) {
            System.out.println(bingeIt.next());
        }
    }
}
