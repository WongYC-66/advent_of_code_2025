import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class day10_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 7
        var lines = MyFileReader.readFile(dir + "d10_input.txt"); // ans: 409

        IO.println(lines.size());
        IO.println();

        // String[] arr = new ArrayList<>();

        List<Long> results = new ArrayList<>();

        for (String line : lines) {
            IO.println(line);
            results.add(findSteps(line));
        }
        IO.println(results);

        long sumRes = results.stream()
                .mapToLong(Long::longValue)
                .sum();

        IO.println("res : " + sumRes);
        IO.println();
    }

    Long findSteps(String line) {
        String[] splitted = line.split(" ");

        String initialStateStr = splitted[0];
        // String costsStr = splitted[splitted.length - 1];
        List<String> optionsStr = Arrays.asList(splitted).subList(1, splitted.length - 1);

        List<Integer> finalState = Arrays.asList(initialStateStr.substring(1, initialStateStr.length() - 1).split(""))
                .stream()
                .mapToInt(str -> str.equals(".") ? 0 : 1)
                .boxed()
                .toList();

        var options = optionsStr.stream()
                .map(str -> str.substring(1, str.length() - 1).split(","))
                .map(strArr -> Arrays.stream(strArr).mapToInt(Integer::parseInt).boxed().toList())
                .toList();

        IO.println();

        IO.println(finalState);
        IO.println(options);
        // bfs until state all = 1

        Deque<State> deque = new ArrayDeque<>();
        ArrayList<Integer> initialState = new ArrayList<>(
                IntStream.range(0, finalState.size()).boxed().map(x -> 0).toList());
        deque.add(new State(new ArrayList<>(initialState), 0L));

        IO.println(initialState);
        IO.println();

        HashSet<String> seen = new HashSet<>();

        while (!deque.isEmpty()) {
            var state = deque.pollFirst();
            var stateInt = state.state();
            var step = state.step();

            var hashKey = toHashKey(stateInt);
            if (seen.contains(hashKey))
                continue;
            seen.add(hashKey);

            // IO.println("----------------------");
            IO.println(stateInt + " -> " + step);
            // IO.println("----------------------");

            if (hasEnded(stateInt, finalState)) {
                IO.println("ended - step = " + step);
                return step;
            }

            // explore each options
            for (var opt : options) {
                var deepCopy = deepCopy(stateInt);

                opt.stream().forEach(idx -> toggle(deepCopy, idx));

                var nextState = new State(deepCopy, step + 1);
                // IO.println(deepCopy);
                deque.addLast(nextState);
            }

        }
        IO.println("ERROR - cant found solution");
        return 0L;
    }

    void toggle(ArrayList<Integer> state, int idx) {
        // IO.println("toggling - " + idx);
        state.set(idx, state.get(idx) ^ 1);
    }

    boolean hasEnded(ArrayList<Integer> state, List<Integer> finalState) {
        return finalState.equals(state);
    }

    ArrayList<Integer> deepCopy(ArrayList<Integer> ori) {
        ArrayList<Integer> arr = new ArrayList<>();
        ori.forEach(v -> arr.add(v));
        return arr;
    }

    String toHashKey(ArrayList<Integer> state) {
        return state.toString();
    }

    public record State(ArrayList<Integer> state, Long step) {
    }

}
