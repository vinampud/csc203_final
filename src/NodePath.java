public class NodePath{
    private Point previous;
    private Point current;
    private int g;
    private double h;
    private double f;

    public NodePath(Point current, int g, double h, Point previous) {
        this.current = current;
        this.g = g;
        this.h = h;
        this.f = g+h;
        this.previous = previous;
    }
    public Point getPrev(){
        return previous; }

    public Point getCurrent() {
        return current; }

    public int getG(){
        return g; }

    public double getH(){
        return h; }

    public double getF(){
        return f; }


    @Override
    public String toString() {
        return "Point: " + current + " g: " + g + " h: " + h + " f: " + f + " previous: " + previous;
    }
}