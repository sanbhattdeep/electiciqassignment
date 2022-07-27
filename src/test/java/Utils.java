import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Iterables.isEmpty;

public class Utils {

    public static boolean isSorted(List<String> listOfStrings) {
        if (isEmpty(listOfStrings) || listOfStrings.size() == 1) {
            return true;
        }

        Iterator<String> iter = listOfStrings.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareToIgnoreCase(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSortedFloat(List<Float> listOfFloats) {
        if (isEmpty(listOfFloats) || listOfFloats.size() == 1) {
            return true;
        }

        Iterator<Float> iter = listOfFloats.iterator();
        Float current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
