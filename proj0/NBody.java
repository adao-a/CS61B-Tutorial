public class NBody {
    /** read the radius of universe */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt(); //number of plantes
        double radius = in.readDouble();
        return  radius;
    }

    /** read the plantes from file */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt(); //number of plantes
        Planet[] planets = new Planet[num];
        double radius = in.readDouble();

        for(int i = 0; i < num; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }

    /** Drawing the Initial Universe State */
    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dT = Double.valueOf(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);

        String backGround = "./images/starfield.jpg";
        double R = readRadius(filename); //universe radius
        StdDraw.setScale(-R,R);
        StdDraw.clear();
        StdDraw.picture(0, 0, backGround);
        for(int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }
        StdDraw.show();

        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0, 0, backGround);
            for(int i = 0; i < planets.length; i++) {
                planets[i].update(dT, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dT;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
