package avl;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Vocab {

  public static void main(String[] args) {

    try {
      // TODO: Change this to make it handle multiple input files.
      File f = new File(args[0]);
      Scanner sc = new Scanner(f);
      Count c = wordCount(sc);
      System.out.println(c);
    } catch (FileNotFoundException exc) {
      System.out.println("Could not find file " + args[0]);
    }
  }

  /* count the total and unique words in a document being read
  * by the given Scanner. return the two values in a Count object.*/
  private static Count wordCount(Scanner sc) {
    AVL tree = new AVL();
    Count c = new Count();

    // TODO: fill in the unique and total fields of c
    // before c is returned

    while (sc.hasNext()) {
      // read and parse each word
      String word = sc.next();

      // remove non-letter characters, convert to lower case:
      word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
      tree.avlInsert(word);
    }
    return c;
  }
}
