import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class day06_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 4277556
        var lines = MyFileReader.readFile(dir + "d6_input.txt"); // ans: 4771265398012

        IO.println(lines.size());
        IO.println();

        Pattern numPattern = Pattern.compile("\\d+");
        Pattern symbolPattern = Pattern.compile("[*+]");

        List<List<Long>> nums = new ArrayList<>();
        List<String> symbols = new ArrayList<>();

        // get + *
        Matcher matcher = symbolPattern.matcher(lines.removeLast());
        while (matcher.find()) {
            symbols.add(matcher.group());
        }
        IO.println(symbols);

        // get [123, 328, 51, 64]
        for (String line : lines) {
            IO.println(line);
            List<Long> tmpList = new ArrayList<>();

            matcher = numPattern.matcher(line);
            while (matcher.find()) {
                tmpList.add(Long.valueOf(matcher.group()));
            }
            nums.add(tmpList);
        }

        IO.println(nums);

        // compute res
        List<Long> results = new ArrayList<>();

        for (int i = 0; i < symbols.size(); i++) {
            var isAddMode = symbols.get(i).equals("+");
            var tmpVal = isAddMode ? 0L : 1L;
            for(var numList : nums){
                var val = numList.get(i);
                if(isAddMode){
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
