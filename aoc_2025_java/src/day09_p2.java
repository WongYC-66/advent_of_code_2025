import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// UN-SOLVED YET
public class day09_p2 {

    String dir = "C:\\dev\\playground\\Advent_of_code_2025\\aoc_2025_java\\src\\";

    void main() {

        var lines = MyFileReader.readFile(dir + "sample.txt"); // ans: 24
        // var lines = MyFileReader.readFile(dir + "d9_input.txt"); // ans: 4749838800
        // too high

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
                // IO.println("------");
                var coord1 = arr.get(i);
                var coord2 = arr.get(j);
                var dx = findDx(coord1, coord2) + 1;
                var dy = findDy(coord1, coord2) + 1;
                var area = dx * dy;
                if (!canFormRec(coord1, coord2, arr)) {
                    IO.println("cannot form " + coord1 + coord2 + " area : " + area);
                    continue;
                }
                IO.println("can form " + coord1 + coord2 + " area : " + area);
                res = Math.max(res, area);
                // IO.println("------");

            }
        }
        // [3,2] - [5,9] forms 24

        IO.println("res : " + res);
        IO.println();
    }

    public record Coord(Long r, Long c) {
    }

    public record Range(Long min, Long max) {
    }

    private Long findDx(Coord c1, Coord c2) {
        return Math.abs(c1.c() - c2.c());
    }

    private Long findDy(Coord c1, Coord c2) {
        return Math.abs(c1.r() - c2.r());
    }

    private boolean canFormRec(Coord coord1, Coord coord2, List<Coord> corners) {
        // given 2 points,
        // jsut check another 2 points within range or not

        // another point is just flip of x/y value
        Coord coord3 = new Coord(coord1.r(), coord2.c());
        Coord coord4 = new Coord(coord2.r(), coord1.c());
        // IO.println(coord3 + " , " + coord4);

        if (!isWithinRange(coord3, corners)) {
            // IO.println("coord3 failed");
            return false;
        }
        if (!isWithinRange(coord4, corners)) {
            // IO.println("coord4 failed");
            return false;
        }
        return true;
    }

    private boolean isWithinRange(Coord coord, List<Coord> corners) {
        // ray casting algo, assuming incoming arr is connected to each other
        // https://medium.com/@girishajmera/exploring-algorithms-to-determine-points-inside-or-outside-a-polygon-038952946f87
        var r = coord.r();
        var c = coord.c();

        int count = 0;
        for (int i = 0; i < corners.size(); i++) {
            var coord1 = corners.get(i);
            var coord2 = corners.get((i + 1) % corners.size());
            var r1 = coord1.r();
            var r2 = coord2.r();
            var c1 = coord1.c();
            var c2 = coord2.c();

            if ((c1 > c) != (c2 > c) && (r < (r2 - r1) * (c - c1) / (c2 - c1) + r1)) {
                count += 1;
            }
            /*
             * def is_point_in_polygon(point, vertices):
             * num_intersections = 0
             * num_vertices = len(vertices)
             * 
             * for i in range(num_vertices):
             * v1 = vertices[i]
             * v2 = vertices[(i + 1) % num_vertices] # wrap around to first vertex for last
             * edge
             * if ((v1[1] > point[1]) != (v2[1] > point[1])) and (point[0] < (v2[0] - v1[0])
             * * (point[1] - v1[1]) / (v2[1] - v1[1]) + v1[0]):
             * num_intersections += 1
             * 
             * return num_intersections % 2 == 1
             */

        }
        // IO.println(coord + " , count : " + count);
        return count % 2 == 1; // odd = within, even = outside
    }

}