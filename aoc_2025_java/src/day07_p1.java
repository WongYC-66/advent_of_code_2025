import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class day07_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 21
        var lines = MyFileReader.readFile(dir + "d7_input.txt"); // ans: 1573

        IO.println(lines.size());
        IO.println();

        List<List<String>> arr = new ArrayList<>();

        // build 2d grid
        for (String line : lines) {
            // IO.println(line);
            var splitted = new ArrayList(Arrays.asList(line.split("")));
            arr.add(splitted);
        }

        printGrid(arr);

        List<Integer> start = findStartPOS(arr);
        IO.println(start);

        int M = arr.size();
        int N = arr.get(0).size();

        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.addLast(start);
        ;

        Long ans = 0L;
        while (queue.size() >= 1) {
            IO.println(queue.size());
            var pos = queue.pollFirst();
            var r = pos.get(0);
            var c = pos.get(1);
            if (r < 0 || r == M || c < 0 || c == N) {
                continue;
            }
            var ch = arr.get(r).get(c);
            if (ch.equals("^")) {
                // pos = splitter
                ans += 1;
                // add lef right
                queue.addLast(List.of(r, c - 1));
                queue.addLast(List.of(r, c + 1));
            }
            if (ch.equals("S") || ch.equals(".")) {
                // go down
                queue.addLast(List.of(r + 1, c));
                // leave a mark
                arr.get(r).set(c, "|");
            }
            if (ch.equals("|")) {
                continue;
            }
        }

        printGrid(arr);

        IO.println("ans : " + ans);
        IO.println();
    }

    void printGrid(List<List<String>> arr) {
        arr.forEach(list -> {
            IO.println(String.join("", list));
        });
    }

    List<Integer> findStartPOS(List<List<String>> arr) {
        Integer r = 0;
        var firstLine = arr.get(0);
        Integer c = null;
        for (Integer i = 0; i < firstLine.size(); i++) {
            if (firstLine.get(i).equals("S")) {
                c = i;
                break;
            }
        }
        return List.of(r, c);
    }

}
