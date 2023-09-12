/**
 * Created By: Jason Wehran
 * Date Created: June 27, 2023
 */
import java.util.Comparator;

public class StringComparator implements Comparator<String> {
    /**
     * Generic method to return the comparison value of two Strings
     */
    public int compare(String s1, String s2) {
        String three1 = s1.substring(0, 3);
        String three2 = s2.substring(0, 3);
        return three1.compareTo(three2);
    }
}
