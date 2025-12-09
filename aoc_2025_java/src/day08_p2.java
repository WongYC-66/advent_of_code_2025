import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.LongStream;

public class day08_p2 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 25272
        var lines = MyFileReader.readFile(dir + "d8_input.txt"); // ans: 9069509600

        IO.println(lines.size());
        IO.println();

        List<List<Integer>> arr = new ArrayList<>();
        var uf = new UnionFind(lines.size());

        for (String line : lines) {
            IO.println(line);
            var splitted = Arrays.asList(line.split(",")).stream().map(Integer::valueOf).toList();
            arr.add(splitted);
        }

        // Deque<day08_QueueDto> queue = new ArrayDeque<>();
        List<day08_QueueDto> dtoArr = new ArrayList<>();

        // check each pair
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (i == j)
                    continue; // self
                var dist = findDistance(arr.get(i), arr.get(j));
                var dto = new day08_QueueDto(i, j, dist);
                dtoArr.add(dto);
            }
        }

        // IO.println(arr);
        dtoArr.sort((a, b) -> (int) (a.dist() - b.dist()));

        // IO.println(dtoArr);
        dtoArr.forEach(dto -> {
            var i = dto.i();
            var j = dto.j();
            // IO.println(String.format("%s , %s , dist = %s", arr.get(i),
            // arr.get(j),dto.dist()));
        });
        IO.println(dtoArr.size());

        int joinCount = 0;
        Long res = 0L;
        for (day08_QueueDto dto : dtoArr) {
            // if (joinCount >= MAX_JOIN_COUNT)
            // break;
            var i = dto.i();
            var j = dto.j();
            if (uf.union(i, j)) {
                joinCount += 1;
                IO.println(String.format("%s - %s - can join", arr.get(i), arr.get(j)));
                res = (long) (arr.get(i).get(0)) * (long) arr.get(j).get(0);
            } else {
                // IO.println(String.format("%s - %s - nothing happened", arr.get(i),
                // arr.get(j)));
            }
        }
        IO.println(joinCount);

        IO.println("res : " + res);
        IO.println();
    }

    Double findDistance(List<Integer> p1, List<Integer> p2) {
        Long sum = 0L;
        for (int i = 0; i < p1.size(); i++) {
            sum += (long) (Math.pow(Math.abs(p1.get(i) - p2.get(i)), 2));
        }
        return Math.sqrt(sum);
    }
}