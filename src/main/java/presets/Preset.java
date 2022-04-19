package main.java.presets;

import main.java.transformations.Brightness;
import main.java.transformations.Saturation;
import main.java.transformations.Transformation;

/**
 * Presets allow applying predefined set of transformations
 */
public enum Preset {
    MONOCHROME(
        "monochrome",
        new Saturation(0),
        new Brightness(70)
    );


    private final Transformation[] transformations;
    private final String name;

    Preset(final String name, Transformation... transformations) {
        this.name = name;
        this.transformations = transformations;
    }

    public static Preset fromName(final String presetName) throws IllegalArgumentException {
        for (Preset preset : values()) {
            if (preset.name.equals(presetName)) {
                return preset;
            }
        }

        throw new IllegalArgumentException(String.format("Preset '%s' not found", presetName));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder transformationsBuilder = new StringBuilder();

        for (int i = 0; i < transformations.length; i++) {
            Transformation transformation = transformations[i];
            if (i == transformations.length - 1) {
                transformationsBuilder.append(String.format("%s", transformation));
            } else {
                transformationsBuilder.append(String.format("%s + ", transformation));
            }
        }

        return String.format("%s [%s]", getName(), transformationsBuilder);
    }

    public Transformation[] getTransformations() {
        return transformations;
    }
}
