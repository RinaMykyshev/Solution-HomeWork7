import java.util.Iterator;
import java.util.List;

public class BingeIterator implements EpisodeIterator {
    private final Iterator<Season> seasonIterator;
    private EpisodeIterator currentEpisodeIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasonIterator = seasons.iterator();
        if (seasonIterator.hasNext()) {
            currentEpisodeIterator = seasonIterator.next().createIterator();
        }
    }

    @Override
    public boolean hasNext() {
        while (currentEpisodeIterator != null) {
            if (currentEpisodeIterator.hasNext()) {
                return true;
            } else if (seasonIterator.hasNext()) {
                currentEpisodeIterator = seasonIterator.next().createIterator();
            } else {
                currentEpisodeIterator = null;
            }
        }
        return false;
    }

    @Override
    public Episode next() {
        return currentEpisodeIterator.next();
    }
}
