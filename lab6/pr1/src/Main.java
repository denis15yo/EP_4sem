import graphics.frames.MainFrame;

public class Main {
    public static void main(String[] args) {
        disableWarnings();
        new MainFrame();
    }


    public static void disableWarnings() {
        System.err.close();
        System.setErr(System.out);
    }
}
