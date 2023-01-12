public class Body {
    public static double G = 6.67e-11;
    public double xxPos;//Its current x position
    public double yyPos;
    public double xxVel;//Its current velocity in the x direction
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** calculate distance*/
    public double calcDistance (Body b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /** calculate force*/
    public double calcForceExertedBy(Body b) {
        double r = this.calcDistance(b);
        double f = G * this.mass * b.mass / (r * r) ;
        return f;
    }

    /** caculate forceX*/
    public double calcForceExertedByX(Body b) {
        double fX = 0;
        double f = this.calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        double r = this.calcDistance(b);

        fX = f * dx / r;
        return fX;
    }

    /** caculate forceX*/
    public double calcForceExertedByY(Body b) {
        double fY = 0;
        double f = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        double r = this.calcDistance(b);

        fY = f * dy / r;
        return fY;
    }

    /** caculate netforceX*/
    public double calcNetForceExertedByX(Body [] allBodys) {
        double fX = 0;

        for (Body b : allBodys) {
            fX += this.calcForceExertedByX(b);
            if (this.equals(b)) {
                continue; //no gravitational force on self
            }
        }
        return fX;
    }

    /** caculate netforceY*/
    public double calcNetForceExertedByY(Body [] allBodys) {
        double fY = 0;

        for (Body b : allBodys) {
            fY += this.calcForceExertedByY(b);
            if (this.equals(b)) {
                continue; //no gravitational force on self
            }
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
}