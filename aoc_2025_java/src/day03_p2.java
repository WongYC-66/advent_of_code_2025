import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class day03_p2 {

    private static String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    public static void main(String[] args) throws Exception {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 3121910778619
        var lines = MyFileReader.readFile(dir + "d3_input.txt"); // ans: 171989894144198
        // 171899565790379 too low
        // 171890471484923 
        // 169724073484065
        // lines.stream().forEach(line -> IO.println(line));

        IO.println(lines.size());
        IO.println();

        Long sum = 0L;
        int FIXED_SIZE = 12;

        for (String line : lines) {
            IO.println(line);

            List<Long> nums = new ArrayList<>(Arrays.asList(line.split("")).stream().map(Long::valueOf).toList());
            List<Long> tmp = new ArrayList<>();

            while (tmp.size() != FIXED_SIZE) {
                for (int i = 9; i >= 1; i--) {
                    var target = Long.valueOf(i);
                    var idxOfTarget = nums.indexOf(target);
                    if (idxOfTarget == -1) {
                        continue; // not found
                    }
                    var remainLen = nums.size() - idxOfTarget;
                    boolean canUse = tmp.size() + remainLen >= FIXED_SIZE;
                    // IO.println(nums + " " + i + " " + canUse);
                    if (canUse) {
                        tmp.add(Long.valueOf(target));
                        nums = nums.subList(idxOfTarget + 1, nums.size());
                        break;
                    }
                }
            }
            Long val = Long.valueOf(String.join("", tmp.stream().map(String::valueOf).toList()));
            IO.println(val);
            sum += val;
        }

        IO.println("ans : " + sum);

        IO.println();
    }
}
