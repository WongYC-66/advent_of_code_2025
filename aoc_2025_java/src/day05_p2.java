import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day05_p2 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 14
        var lines = MyFileReader.readFile(dir + "d5_input.txt"); // ans: 347338785050515
        // 5077302989906 too low
        // lines.stream().forEach(line -> IO.println(line));

        IO.println(lines.size());
        IO.println();

        List<List<Long>> intervals = new ArrayList<>();

        for (String line : lines) {
            // IO.println(line);
            var isRange = line.contains("-");
            if (!isRange)
                continue;
            intervals.add(Arrays.stream(line.split("-")).map(Long::valueOf).toList());
        }

        // IO.println(intervals);

        // sort by the first digit
        intervals.sort((a, b) -> {
            var aS = a.get(0);
            var bS = b.get(0);
            if (aS < bS) {
                return -1;
            }
            if (aS == bS) {
                return 0;
            }
            return 1;
        });
        // IO.println(intervals);

        // merge overlapping inervals
        List<List<Long>> merged = new ArrayList<>();
        intervals.forEach(interval -> {
            if (merged.size() == 0) {
                merged.add(interval);
                return;
            }

            var prevS = merged.getLast().get(0);
            var prevE = merged.getLast().get(1);

            var currS = interval.get(0);
            var currE = interval.get(1);

            var overlapped = hasOverlapped(prevS, prevE, currS, currE);
            if (overlapped) {
                merged.removeLast();
                merged.add(List.of(prevS, Math.max(prevE, currE)));
            } else {
                merged.add(interval);
            }

        });

        // IO.println(merged);

        Long ans = 0L;
        for (var interval : merged) {
            var currS = interval.get(0);
            var currE = interval.get(1);
            var count = currE - currS + 1L;
            ans += count;
        }
        ;

        merged.forEach(IO::println);

        IO.println("ans : " + ans);
        IO.println();
    }

    boolean hasOverlapped(Long prevS, Long prevE, Long currS, Long currE) {
        return currS <= prevE;
    }

}
