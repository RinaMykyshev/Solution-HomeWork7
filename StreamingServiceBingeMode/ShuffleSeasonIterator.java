import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffled;
    private int current = 0;

    public ShuffleSeasonIterator(List<Episode> episodes, long seed) {
        this.shuffled = new ArrayList<>(episodes);
        Collections.shuffle(shuffled, new Random(seed));
    }

    @Override
    public boolean hasNext() {
        return current < shuffled.size();
    }

    @Override
    public Episode next() {
        return shuffled.get(current++);
    }
}
