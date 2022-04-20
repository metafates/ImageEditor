package wrappers;

/**
 * Size wrapper class
 */
public class Size {
    private final int width;
    private final int height;

    /**
     * Constructs size instance from separated width and height
     *
     * @param width  Size width
     * @param height Size height
     */
    public Size(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs size instance from string in a format of [width]x[height] (e.g. 1920x1080)
     *
     * @param sizeString String in a format of [width]x[height]
     * @throws IllegalArgumentException When string has any other format
     */
    public Size(final String sizeString) throws IllegalArgumentException {
        if (!sizeString.matches("\\d+x\\d+")) {
            throw new IllegalArgumentException(String.format("'%s' does not match '[width]x[height]' format", sizeString));
        }

        String[] dimensions = sizeString.split("x");

        this.width = Integer.parseInt(dimensions[0]);
        this.height = Integer.parseInt(dimensions[1]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
