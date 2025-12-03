import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day01_p1 {

    private static String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025\\src\\";

    public static void main(String[] args) throws Exception {

        var lines = MyFileReader.readFile(dir + "d1_input.txt");
        // lines.stream().forEach(line -> IO.println(line));

        // regex hehe
        Pattern pattern = Pattern.compile("(L|R)(\\d+)", Pattern.CASE_INSENSITIVE);

        Integer initVal = 50;
        Integer res = 0;

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            var dir = matcher.group(1); // e.g. L/R
            var val = Integer.valueOf(matcher.group(2)); // 1~99

            initVal += dir.equals("L") ? -val : +val;
            initVal %= 100;

            if (initVal == 0) {
                res += 1;
            }
            IO.println(initVal + " " + res + " " + dir + " " + val);
        }

        IO.println();
        IO.println(String.format("res : %s", res.toString()));
    }

}
