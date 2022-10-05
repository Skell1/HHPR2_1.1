import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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
    private BigDecimal efficiency = BigDecimal.ZERO;
    private int totalArea = 0;

    public Region(BigDecimal efficiency, int totalArea, int area) {
        this.efficiency = efficiency;
        this.totalArea = totalArea;
        this.area = area;
    }

    public Region() {
    }

    public int getArea() {
        return area;
    }

    public BigDecimal getEfficiency() {
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

    private static Region checkPlace(String[][] matrix, int x, int y) {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell(x,y));

        int xLength = matrix.length;
        int yLength = matrix[0].length;

        int minX = x,minY = y, maxX = x, maxY = y;
        int area = 0;

        while (!cells.isEmpty()) {
            Cell cell = cells.remove(0);
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

            cells.add(new Cell(x+1,y));
            cells.add(new Cell(x-1,y));
            cells.add(new Cell(x,y-1));
            cells.add(new Cell(x,y+1));
            cells.add(new Cell(x+1,y-1));
            cells.add(new Cell(x+1,y+1));
            cells.add(new Cell(x-1,y-1));
            cells.add(new Cell(x-1,y+1));

            removeNonMutchingCell(cells,matrix);


        }
        int totalArea = (maxX-minX+1)*(maxY-minY+1);
        BigDecimal efficiency = new BigDecimal(area).divide(new BigDecimal(totalArea), 13, RoundingMode.HALF_UP);
        //double efficiency = (double) area / totalArea;
        //BigDecimal bigDecimal = new BigDecimal(efficiency).setScale(13, RoundingMode.HALF_UP);
        return new Region(efficiency,totalArea,area);
    }

    private static void removeNonMutchingCell(List<Cell> cells, String[][] matrix){
        cells.removeIf(cell -> cellDoesNotMatch(cell,matrix));
    }

    private static boolean cellDoesNotMatch(Cell cell, String[][] matrix){
        return cell.getX() < 0 || cell.getY() < 0 || cell.getX() >= matrix.length || cell.getY() >= matrix[0].length || matrix[cell.getX()][cell.getY()].equals("0");
    }
    public static int findRegion(String[][] matrix) {
        int xLength = matrix.length;
        if (xLength == 0) return 0;
        int yLength = matrix[0].length;
        Region region = new Region();

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (matrix[i][j].equals("1")) {
                    Region curReg = checkPlace(matrix, i, j);
                    if (curReg.getArea() >= 2) {
                        if (curReg.getEfficiency().compareTo(region.getEfficiency()) > 0) {
                            //if (curReg.getEfficiency() > region.getEfficiency()) {
                            region = curReg;
                        } else if (curReg.getTotalArea() > region.getTotalArea() && curReg.getEfficiency().compareTo(region.getEfficiency())==0) {
                            region = curReg;
                        }
                    }
                }
            }
        }
        return region.getEfficiency()!=BigDecimal.ZERO ? region.getTotalArea() : 0;
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


