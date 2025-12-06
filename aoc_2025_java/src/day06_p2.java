import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class day06_p2 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 3263827
        var lines = MyFileReader.readFile(dir + "d6_input.txt"); // ans: 10695785245101

        IO.println(lines.size());
        IO.println();

        Pattern symbolPattern = Pattern.compile("[*+]");

        List<List<Long>> nums = new ArrayList<>();
        List<String> symbols = new ArrayList<>();

        // get + *
        Matcher matcher = symbolPattern.matcher(lines.removeLast());
        while (matcher.find()) {
            symbols.add(matcher.group());
        }
        IO.println(symbols);

        List<Long> numList = new ArrayList<>();
        for (int i = 0; i < lines.get(0).length(); i++) {
            List<String> tmp = new ArrayList<>();
            for (String line : lines) {
                tmp.add(String.valueOf(line.charAt(i)));
            }
            String joined = String.join("", tmp);
            // IO.println(i + " : " + joined);

            if (joined.isBlank()) { // end of column
                nums.add(numList);
                numList = new ArrayList<>();
            } else {
                numList.add(Long.valueOf(joined.trim()));
            }
        }

        // at end of last char
        nums.add(numList);

        IO.println(nums);

        // compute res
        List<Long> results = new ArrayList<>();

        for (int i = 0; i < symbols.size(); i++) {
            var isAddMode = symbols.get(i).equals("+");
            var tmpVal = isAddMode ? 0L : 1L;
            var listOfNum = nums.get(i);
            for (var val : listOfNum) {
                if (isAddMode) {
                    tmpVal += val;
                } else {
                    tmpVal *= val;
                }
            }
            results.add(tmpVal);
        }
        IO.println(results);

        // sum each column res
        Long ans = results.stream().collect(Collectors.summingLong(Long::longValue));
        IO.println("ans : " + ans);
        IO.println();
    }

}
