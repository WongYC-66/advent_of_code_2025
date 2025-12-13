import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class day11_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";
    String startPos = "you";
    String finalPos = "out";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 5
        var lines = MyFileReader.readFile(dir + "d11_input.txt"); // ans: 539;

        IO.println(lines.size());
        IO.println();

        HashMap<String, List<String>> graph = new HashMap<>();
        HashMap<String, Long> memo = new HashMap<>();

        for (String line : lines) {
            IO.println(line);
            var splitted = line.split(":");
            var from = splitted[0];
            var to = Arrays.stream(splitted[1].trim().split(" ")).toList();
            graph.put(from, to);
        }

        IO.println(graph);

        Long res = findWayOut(startPos, graph, memo);

        IO.println("res : " + res);
        IO.println();
    }

    Long findWayOut(String currPos, HashMap<String, List<String>> graph, HashMap<String, Long> memo) {
        IO.println(currPos);
        if (currPos.equals(finalPos)) {
            return 1L;
        }

        if (memo.containsKey(currPos)) {
            return memo.get(currPos);
        }

        Long res = 0L;

        if (graph.containsKey(currPos)) {
            for (var nextPos : graph.get(currPos)) {
                res += findWayOut(nextPos, graph, memo);
            }
        }

        memo.put(currPos, res);
        return res;
    }

}
