/**
 * Created by katundwa on 018, 02/18/15.
 */
public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        while (k > 0)
        {
            StdOut.println(q.dequeue());
            k--;
        }
        return;
    }
}
