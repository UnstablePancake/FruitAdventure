public abstract class GameObject {

    private int width;
    private int height;
    public double x;
    public double y;
    public double xVel;
    public double yVel;

    public GameObject() {
        width = 20;
        height = 5;
        x = (Window.FRAME_WIDTH / 2) - (width / 2);
        y = Window.FRAME_HEIGHT - 75;
    }

    public GameObject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }
}
