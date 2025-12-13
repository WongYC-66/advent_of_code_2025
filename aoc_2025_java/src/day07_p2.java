import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class day07_p2 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 40
        var lines = MyFileReader.readFile(dir + "d7_input.txt"); // ans: 15093663987272

        IO.println(lines.size());
        IO.println();

        List<List<String>> arr = new ArrayList<>();

        HashMap<String, Long> memo = new HashMap<>(); /// cachinggg

        // build 2d grid
        for (String line : lines) {
            // IO.println(line);
            var splitted = new ArrayList<String>(Arrays.asList(line.split("")));
            arr.add(splitted);
        }

        printGrid(arr);

        List<Integer> start = findStartPOS(arr);
        IO.println(start);

        // Deque<List<Integer>> queue = new ArrayDeque<>();
        Long ans = countTimelineFromPos(start, arr, memo);

        printGrid(arr);

        IO.println("ans : " + ans);
        IO.println();
    }

    Long countTimelineFromPos(List<Integer> pos, List<List<String>> arr, HashMap<String, Long> memo) {
        // IO.println(pos);
        var M = arr.size();
        var r = pos.get(0);
        var c = pos.get(1);
        var ch = arr.get(r).get(c);
        var key = String.format("%s,%s", r, c);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (r >= M - 1) {
            return 1L; // reached bottom
        }

        Long res = 0L;

        if (ch.equals("^")) {
            // "^" get the split result of left + right
            res = 0L + countTimelineFromPos(List.of(r, c - 1), arr, memo)
                    + countTimelineFromPos(List.of(r, c + 1), arr, memo);
        } else {
            // "S" or "." - go down
            res = 0L + countTimelineFromPos(List.of(r + 1, c), arr, memo);
        }

        memo.put(key, res); // caching

        return res;
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
