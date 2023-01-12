public class Planet {
    private static double G = 6.67e-11;
    public double xxPos;//Its current x position
    public double yyPos;
    public double xxVel;//Its current velocity in the x direction
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** calculate distance*/
    public double calcDistance (Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /** calculate force*/
    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double f = G * this.mass * p.mass / (r * r) ;
        return f;
    }

    /** caculate forceX*/
    public double calcForceExertedByX(Planet p) {
        double fX = 0;
        double f = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);

        fX = f * dx / r;
        return fX;
    }

    /** caculate forceX*/
    public double calcForceExertedByY(Planet p) {
        double fY = 0;
        double f = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);

        fY = f * dy / r;
        return fY;
    }

    /** caculate netforceX*/
    public double calcNetForceExertedByX(Planet [] allPlanets) {
        double fX = 0;

        for (Planet p : allPlanets) {

            if (this.equals(p)) {
                continue; //no gravitational force on self
            }
            fX += this.calcForceExertedByX(p);
        }
        return fX;
    }

    /** caculate netforceY*/
    public double calcNetForceExertedByY(Planet [] allPlanets) {
        double fY = 0;

        for (Planet p : allPlanets) {

            if (this.equals(p)) {
                continue; //no gravitational force on self
            }
            fY += this.calcForceExertedByY(p);
        }
        return fY;
    }

    /** update*/
    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * xxVel;
        this.yyPos = this.yyPos + dt * yyVel;

    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
    }

}
