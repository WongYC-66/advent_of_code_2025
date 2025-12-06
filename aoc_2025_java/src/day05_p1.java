import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day05_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 3
        var lines = MyFileReader.readFile(dir + "d5_input.txt"); // ans: 520
        // lines.stream().forEach(line -> IO.println(line));

        IO.println(lines.size());
        IO.println();

        List<List<Long>> intervals = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        for (String line : lines) {
            IO.println(line);
            if (line.length() == 0)
                continue;
            var isRange = line.contains("-");
            switch (isRange) {
                case true -> intervals.add(Arrays.stream(line.split("-")).map(Long::valueOf).toList());
                default -> ids.add(Long.valueOf(line));
            }
        }

        IO.println(intervals);
        IO.println(ids);

        // checking
        int ans = 0;
        for (Long id : ids) {

            for (List<Long> interval : intervals) {
                Long start = interval.get(0);
                Long end = interval.get(1);
                if (start <= id && id <= end) {
                    ans += 1;
                    break;
                }
            }
        }

        IO.println("ans : " + ans);
        IO.println();
    }

}
