package avl;

/**
 * Encapsulates two counts related to a document: the total number of words and the number of unique words.
 */
public class Count {
    public int total;
    public int unique;

    /**
     * constructor: initalize counts to 0
     */
    public Count() {
        total = 0;
        unique = 0;
    }

    /**
     * constructor: initalize counts per parameters
     */
    public Count(int tot, int uniq) {
        total = tot;
        unique = uniq;
    }

    /**
     * returns a string showing both counts
     */
    public String toString() {
        return "" + unique + " " + total;
    }

}
