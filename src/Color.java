/**
 * Created by gu on 2016/3/19.
 */
public enum Color {
    white, yellow, red, blue, orange, green;

    @Override
    public String toString() {
        switch (this) {
            case white:
                return "white ";
            case yellow:
                return "yellow";
            case red:
                return "red   ";
            case blue:
                return "blue  ";
            case orange:
                return "orange";
            case green:
                return "green ";
            default:
                return "error ";
        }
    }

}
