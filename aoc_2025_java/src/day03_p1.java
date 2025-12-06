import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day03_p1 {

    private static String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    public static void main(String[] args) throws Exception {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans:357
        var lines = MyFileReader.readFile(dir + "d3_input.txt"); // ans: 17321
        // lines.stream().forEach(line -> IO.println(line));

        IO.println(lines.size());
        IO.println();

        Long sum = 0L;

        for (String line : lines) {
            List<Long> arr = new ArrayList<>(Arrays.asList());

            var nums = Arrays.asList(line.split("")).stream().map(Long::valueOf).toList();

            
            IO.println(line);
            nums = nums.reversed();
            IO.println(nums);

            nums.forEach(n -> {
               
                if(arr.size() <= 1){
                    arr.addFirst(n);
                    return;
                }

                // IO.println(n);
                if(n < arr.getFirst()){
                    return;
                }

                IO.println(arr);

                // pop the smalelr one
                var smallerIdx = arr.getFirst() < arr.getLast()? 0 : 1;
                arr.remove(smallerIdx);
                arr.addFirst(n);

            });
            String valsStr = String.join("", arr.stream().map(String::valueOf).toList());
            IO.println(valsStr);
            Long vals = Long.valueOf(valsStr);
            sum += vals;

            IO.println(arr);

        }

        IO.println("ans : " + sum);

        IO.println();
    }
}
