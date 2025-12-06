import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day04_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main(){

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans:13
        var lines = MyFileReader.readFile(dir + "d4_input.txt"); // ans: 1540
        // lines.stream().forEach(line -> IO.println(line));

        IO.println(lines.size());
        IO.println();

        List<List<String>> arr = new ArrayList<>();

        for (String line : lines) {
            IO.println(line);
            List<String> lineArr = Arrays.asList(line.split(""));
            arr.add(lineArr);
        }

        int M = arr.size();
        int N = arr.get(0).size();

        int ans = 0;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (canAccess(r, c, arr)) {
                    IO.println(String.format("r: %s, c: %s", r, c));
                    ans += 1;
                }
            }
        }

        IO.println("ans : " + ans);
        IO.println();
    }

    boolean canAccess(int r, int c, List<List<String>> arr) {
        if(arr.get(r).get(c).equals(".")){
            return false;
        }

        int M = arr.size();
        int N = arr.get(0).size();

        int MAX = 3;
        int count = 0;

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}; // Change in row
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1}; // Change in column

        for(int i = 0 ; i < dr.length ; i++){
            int nR = r + dr[i];
            int nC = c + dc[i];
            if(nR < 0 || nR == M || nC < 0 || nC == N){
                continue;
            }
            if(arr.get(nR).get(nC).equals("@")){
                count += 1;
            }
        }
        return count <= MAX;
    }

}
