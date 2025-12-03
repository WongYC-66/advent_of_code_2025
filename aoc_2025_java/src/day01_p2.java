import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day01_p2 {

    private static String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025\\src\\";

    public static void main(String[] args) throws Exception {

        var lines = MyFileReader.readFile(dir + "d1_input.txt");
        // var lines = MyFileReader.readFile(dir + "sample.txt");
        // lines.stream().forEach(line -> IO.println(line));

        // regex hehe
        Pattern pattern = Pattern.compile("(L|R)(\\d+)", Pattern.CASE_INSENSITIVE);

        Integer curr = 50;
        Integer res = 0;

        IO.println();
        IO.println(res + " " + curr);

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            var dir = matcher.group(1); // e.g. L/R
            var val = Integer.valueOf(matcher.group(2)); // 1~99

            if(dir.equals("L") && curr != 0 && val >= curr){
                res += 1;
            }

            curr += dir.equals("L") ? -val : +val;
            // IO.println(curr);

            int rotation = (int) (Math.abs(curr) / 100);
            res += rotation;

            curr %= 100;

            if (curr < 0) {
                curr += 100;
            }

            IO.println(res + " " + curr + " " + dir + " " + val);
        }

        IO.println();
        IO.println(String.format("res : %s", res.toString()));
    }

    // 6659 wrong
}
