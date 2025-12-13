import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day09_p1 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        // var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 50
        var lines = MyFileReader.readFile(dir + "d9_input.txt"); // ans: 4749838800

        IO.println(lines.size());
        IO.println();

        List<Coord> arr = new ArrayList<>();

        for (String line : lines) {
            IO.println(line);
            var splitted = Arrays.asList(line.split(",")).stream().map(Long::valueOf).toList();
            arr.add(new Coord(splitted.get(1), splitted.get(0)));
        }
        // IO.println(arr);

        Long res = 0L;
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                var coord1 = arr.get(i);
                var coord2 = arr.get(j);
                var dx = findDx(coord1, coord2) + 1;
                var dy = findDy(coord1, coord2) + 1;
                var area = dx * dy;
                res = Math.max(res, area);
            }
        }

        IO.println("res : " + res);
        IO.println();
    }

    public record Coord(Long r, Long c) {
    }

    private Long findDx(Coord c1, Coord c2) {
        return Math.abs(c1.c() - c2.c());
    }

    private Long findDy(Coord c1, Coord c2) {
        return Math.abs(c1.r() - c2.r());
    }

    
}