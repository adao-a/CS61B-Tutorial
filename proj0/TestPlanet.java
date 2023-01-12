public class TestPlanet {
    public static void main(String[] args) {
        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        System.out.println("p1_ForceByXY: "+p1.calcForceExertedBy(p2));
        System.out.println("p2_ForceByXY； "+p2.calcForceExertedBy(p1));
    }
}
