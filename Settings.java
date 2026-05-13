public class Settings {
    // Game Settings
    public final int width = 800;
    public final int height = 600;

    // Player Settings
    public final int pWidth = 32;
    public final int pHeight = 32;
    public final int speed = 5;
    public final int jump = 15;
    public final int gravity = 1;

    // Magnet setting
    public final double maxDistance = 50;
    public double force(double distance) {
        return 100 / (distance * distance); // magnetic force setting
    }
}
