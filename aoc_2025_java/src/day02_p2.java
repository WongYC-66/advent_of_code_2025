import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class day02_p2 {

    private static String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025\\src\\";

    public static void main(String[] args) throws Exception {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans:4174379265
        var lines = MyFileReader.readFile(dir + "d2_input.txt"); // ans: ?
        // lines.stream().forEach(line -> IO.println(line));
        lines = Arrays.asList(lines.get(0).split(","));

        IO.println();
        IO.println(lines.size());

        long ans = 0L;

        for (String line : lines) {
            IO.println(line);

            String[] splitted = line.split("-");
            var start = Long.valueOf(splitted[0]);
            var end = Long.valueOf(splitted[1]);

            long[] invalidIds = LongStream.rangeClosed(start, end)
                    .filter(num -> isInvalidNum(num))
                    .toArray();

            Long invalidSum = Arrays.stream(invalidIds)
                    .sum();

            // IO.println(invalidSum);
            ans += invalidSum;
        }

        IO.println("ans : " + ans);

        IO.println();
    }

    public static boolean isInvalidNum(long num) {
        String str = String.valueOf(num);
        Pattern pattern = Pattern.compile("^(\\d+)(\\1)+$");
        Matcher matcher = pattern.matcher(str);
        boolean found = matcher.find();

        // IO.println(str);

        if (!found) {
            return false;
        }
        // var part1 = matcher.group(1);
        // var part2 = matcher.group(2);

        IO.println("found - " + str);
        // IO.println(part1 + " " + part2);

        return true;
    }

}
