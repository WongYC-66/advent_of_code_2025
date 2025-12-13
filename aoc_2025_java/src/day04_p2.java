import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class day04_p2 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";
    int ALLOWABLE_COUNT = 3;

    int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 }; // Change in row
    int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 }; // Change in column

    // TOPOLOGICAL SORT INSTINCT

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 43
        var lines = MyFileReader.readFile(dir + "d4_input.txt"); // ans: 8972
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

        List<List<Integer>> countArr = initCountArr(M, N);
        Deque<List<Integer>> deque = new ArrayDeque<>();

        // IO.println(countArr);

        // fill in countArr + add initial removable coordinates
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                String symbol = arr.get(r).get(c);
                if (!symbol.equals("@")) {
                    continue;
                }
                int count = countNearbyRoll(r, c, arr);
                countArr.get(r).set(c, count);
                if (count <= ALLOWABLE_COUNT) {
                    deque.addLast(List.of(r, c));
                }
            }
        }

        // labelled newly added as "."
        deque.forEach(list -> {
            Integer r = list.get(0);
            Integer c = list.get(1);
            arr.get(r).set(c, ".");
        });
        ;

        print2D(countArr);

        // print arr 2d grid
        arr.stream().forEach(line -> {
            IO.println(String.join("", line));
        });

        int ans = 0;
        while (deque.size() >= 1) {
            List<Integer> coords = deque.pollFirst();
            Integer r = coords.get(0);
            Integer c = coords.get(1);

            ans += 1;
            countArr.get(r).set(c, 0);
            arr.get(r).set(c, ".");

            // decrease nearby(8dirs) count by 1
            decreaseNearbyCount(r, c, arr, countArr);
            addNearbyToQueueIfCanRemove(r, c, arr, countArr, deque);
            IO.println(String.format("[%s , %s]", r, c));

        }

        // print final 2d grid
        arr.stream().forEach(line -> {
            IO.println(String.join("", line));
        });

        print2D(countArr);

        IO.println("ans : " + ans);
        IO.println();
    }

    void print2D(List<List<Integer>> countArr) {
        // print count 2d arr
        countArr.stream().forEach(line -> {
            IO.println(line);
        });
    }

    List<List<Integer>> initCountArr(int M, int N) {
        List<List<Integer>> countArr = IntStream.range(0, M)
                .mapToObj(i -> IntStream.range(0, N)
                        .map(j -> 0)
                        .boxed() // convert int to Integer
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        return countArr;
    }

    int countNearbyRoll(int r, int c, List<List<String>> arr) {
        if (arr.get(r).get(c).equals("."))
            return 0;
        int M = arr.size();
        int N = arr.get(0).size();

        int count = 0;

        for (int i = 0; i < dr.length; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if (nR < 0 || nR == M || nC < 0 || nC == N) {
                continue;
            }
            if (arr.get(nR).get(nC).equals("@")) {
                count += 1;
            }
        }
        return count;
    }

    void decreaseNearbyCount(int r, int c, List<List<String>> arr, List<List<Integer>> countArr) {
        int M = arr.size();
        int N = arr.get(0).size();
        for (int i = 0; i < dr.length; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if (nR < 0 || nR == M || nC < 0 || nC == N) {
                continue;
            }
            Integer prevCount = countArr.get(nR).get(nC);
            countArr.get(nR).set(nC, prevCount - 1);
        }
    }

    void addNearbyToQueueIfCanRemove(int r, int c, List<List<String>> arr, List<List<Integer>> countArr,
            Deque<List<Integer>> deque) {
        int M = arr.size();
        int N = arr.get(0).size();
        for (int i = 0; i < dr.length; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if (nR < 0 || nR == M || nC < 0 || nC == N) {
                continue;
            }
            if (!arr.get(nR).get(nC).equals("@")) {
                continue;
            }
            if (countArr.get(nR).get(nC) <= ALLOWABLE_COUNT) {
                // add task and label as "." to avoid duplicate
                deque.addLast(List.of(nR, nC));
                arr.get(nR).set(nC, ".");
            }
        }
    }

}
