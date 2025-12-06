import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class day03_p2 {

    private static String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    public static void main(String[] args) throws Exception {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 3121910778619
        // var lines = MyFileReader.readFile(dir + "test.txt"); 
        var lines = MyFileReader.readFile(dir + "d3_input.txt"); // ans: 171899565790379 too low
                                                                        //  171890471484923
        // lines.stream().forEach(line -> IO.println(line));

        IO.println(lines.size());
        IO.println();

        Long sum = 0L;
        int FIXED_SIZE = 12;
        // int FIXED_SIZE = 5;

        for (String line : lines) {
            List<Long> arr = new ArrayList<>(Arrays.asList());

            var nums = Arrays.asList(line.split("")).stream().map(Long::valueOf).toList();

            
            IO.println(line);
            nums = nums.reversed();
            // IO.println(nums);

            nums.forEach(n -> {
               
                if(arr.size() < FIXED_SIZE){
                    arr.addFirst(n);
                    return;
                }

                // IO.println(n);
                if(n < arr.getFirst()){
                    return;
                }

                // IO.println(arr);

                // pop the smalelr one
                var smallerIdx = getTheSmallestIdx(arr);

                // IO.println(String.format("Before  remove : %s ", arr));
                
                arr.remove(smallerIdx);
                arr.addFirst(n);

                // 8115119
                // 9815119
                // 9811519

                // IO.println(String.format("After  remove  and add : %s ", arr));

            });
            String valsStr = String.join("", arr.stream().map(String::valueOf).toList());
            IO.println(valsStr);
            Long vals = Long.valueOf(valsStr);
            sum += vals;

            // IO.println(arr);

        }

        IO.println("ans : " + sum);

        IO.println();
    }

    public static int getTheSmallestIdx(List<Long> nums){
        Long min = nums.stream().min(Long::compare).orElse(null);
        return nums.indexOf(min);
    }
}
