import java.util.Scanner;
class Region {
    private int minX,minY,maxX,maxY;
    private int area = 0;
    private double efficiency = 0;
    private int totalArea;
    public Region(int x,int y) {
        minX = x;
        minY = y;
        maxY = y;
        maxX = x;
        findTotalArea();
    }
    public Region() {
        findTotalArea();
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getArea() {
        return area;
    }

    public void incrArea() {
        area+=1;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency() {
        findTotalArea();
        if (area>2) {
            efficiency = (double) area / getTotalArea();
        }
    }

    public int getTotalArea() {
        return totalArea;
    }

    public void findTotalArea() {
        this.totalArea = (maxX-minX+1)*(maxY-minY+1);
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

    private static Region checkPlace(String[][] matrix, int x, int y, Region region) {
        int xLength = matrix.length;
        int yLength = matrix[0].length;

        if (x < 0 || y < 0 || x >= xLength || y >= yLength || matrix[x][y].equals("0")) {
            return region;
        }
        region.incrArea();
        matrix[x][y] = "0";
        if (x < region.getMinX()) region.setMinX(x);
        if (x > region.getMaxX()) region.setMaxX(x);
        if (y < region.getMinY()) region.setMinY(y);
        if (y > region.getMaxY()) region.setMaxY(y);

        region = checkPlace(matrix, x - 1, y, region); //вверх
        region = checkPlace(matrix, x + 1, y, region); //вниз
        region = checkPlace(matrix, x, y - 1, region); //влево
        region = checkPlace(matrix, x, y + 1, region); //вправо
        region = checkPlace(matrix, x - 1, y - 1, region); //вверх влево
        region = checkPlace(matrix, x - 1, y + 1, region); //вверх вправо
        region = checkPlace(matrix, x + 1, y - 1, region); //вниз влево
        region = checkPlace(matrix, x + 1, y + 1, region); //вниз вправо
        return region;
    }

    public static int findRegion(String[][] matrix) {
        int xLength = matrix.length;
        if (xLength == 0) return 0;
        int yLength = matrix[0].length;
        Region region = new Region();

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (matrix[i][j].equals("1")) {
                    Region curReg = new Region(i, j);
                    curReg = checkPlace(matrix, i, j, curReg);
                    curReg.setEfficiency();
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


