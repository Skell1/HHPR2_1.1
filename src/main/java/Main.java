import java.util.ArrayList;
import java.util.Scanner;

class Cell {
    private final int x,y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
class Region {
    private int area = 0;
    private double efficiency = 0;
    private int totalArea = 0;

    public Region(double efficiency, int totalArea, int area) {
        this.efficiency = efficiency;
        this.totalArea = totalArea;
        this.area = area;
    }

    public Region() {
    }

    public int getArea() {
        return area;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public int getTotalArea() {
        return totalArea;
    }

}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        String[] firstLine = num.split(" ");
        short n = Short.parseShort(firstLine[0]);
        short m = Short.parseShort(firstLine[1]);
        var map = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] splitLine = in.nextLine().split(" ");
            map[i] = splitLine.clone();
        }
        in.close();
        System.out.println(findRegion(map));
    }

    private static Region checkPlace2(String[][] matrix, int x, int y) {
        ArrayList<Cell> a = new ArrayList<>();
        a.add(new Cell(x,y));

        int xLength = matrix.length;
        int yLength = matrix[0].length;

        int minX = x,minY = y, maxX = x, maxY = y;
        int area = 0;

        while (!a.isEmpty()) {
            Cell cell = a.remove(0);
            x = cell.getX();
            y = cell.getY();

            if (x<minX) minX = x;
            if (x>maxX) maxX = x;
            if (y<minY) minY = y;
            if (y>maxY) maxY = y;

            if (x < 0 || y < 0 || x >= xLength || y >= yLength || matrix[x][y].equals("0")) {
                continue;
            }

            area+=1;
            matrix[x][y] = "0";

            Cell up = new Cell(x+1,y);
            Cell down = new Cell(x-1,y);
            Cell left = new Cell(x,y-1);
            Cell right = new Cell(x,y+1);
            Cell upLeft = new Cell(x+1,y-1);
            Cell upRight = new Cell(x+1,y+1);
            Cell downLeft = new Cell(x-1,y-1);
            Cell downRight = new Cell(x-1,y+1);

            if (!(up.getX() < 0 || up.getY() < 0 || up.getX() >= xLength || up.getY() >= yLength || matrix[up.getX()][up.getY()].equals("0"))) {
                a.add(up);
            }
            if (!(down.getX() < 0 || down.getY() < 0 || down.getX() >= xLength || down.getY() >= yLength || matrix[down.getX()][down.getY()].equals("0"))) {
                a.add(down);
            }
            if (!(left.getX() < 0 || left.getY() < 0 || left.getX() >= xLength || left.getY() >= yLength || matrix[left.getX()][left.getY()].equals("0"))) {
                a.add(left);
            }
            if (!(right.getX() < 0 || right.getY() < 0 || right.getX() >= xLength || right.getY() >= yLength || matrix[right.getX()][right.getY()].equals("0"))) {
                a.add(right);
            }
            if (!(upLeft.getX() < 0 || upLeft.getY() < 0 || upLeft.getX() >= xLength || upLeft.getY() >= yLength || matrix[upLeft.getX()][upLeft.getY()].equals("0"))) {
                a.add(upLeft);
            }
            if (!(upRight.getX() < 0 || upRight.getY() < 0 || upRight.getX() >= xLength || upRight.getY() >= yLength || matrix[upRight.getX()][upRight.getY()].equals("0"))) {
                a.add(upRight);
            }
            if (!(downLeft.getX() < 0 || downLeft.getY() < 0 || downLeft.getX() >= xLength || downLeft.getY() >= yLength || matrix[downLeft.getX()][downLeft.getY()].equals("0"))) {
                a.add(downLeft);
            }
            if (!(downRight.getX() < 0 || downRight.getY() < 0 || downRight.getX() >= xLength || downRight.getY() >= yLength || matrix[downRight.getX()][downRight.getY()].equals("0"))) {
                a.add(downRight);
            }

        }
        int totalArea = (maxX-minX+1)*(maxY-minY+1);
        double efficiency = (double) area / totalArea;
        return new Region(efficiency,totalArea,area);
    }

    public static int findRegion(String[][] matrix) {
        int xLength = matrix.length;
        if (xLength == 0) return 0;
        int yLength = matrix[0].length;
        Region region = new Region();

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (matrix[i][j].equals("1")) {
                    Region curReg = checkPlace2(matrix, i, j);
                    if (curReg.getArea() >= 2) {
                        if (curReg.getEfficiency() > region.getEfficiency()) {
                            region = curReg;
                        } else if (curReg.getTotalArea() > region.getTotalArea() && curReg.getEfficiency()==region.getEfficiency()) {
                            region = curReg;
                        }
                    }
                }
            }
        }
        return region.getEfficiency() > 0 ? region.getTotalArea() : 0;
    }
    //}
    public static int test(String text) {
        Scanner in = new Scanner(text);
        String num = in.nextLine();
        String[] firstLine = num.split(" ");
        short n = Short.parseShort(firstLine[0]);
        short m = Short.parseShort(firstLine[1]);
        var map = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] splitLine = in.nextLine().split(" ");
            map[i] = splitLine.clone();
        }
        in.close();
        return findRegion(map);
    }
}


