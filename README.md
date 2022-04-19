# Climage - CLI Image Editor

- [About](#about)
- [Note](#note)
- [Functionality](#functionality)
- [Usage](#usage)
- [Build](#build)
- [Examples](#examples)

## About

This is an image editor with various image editing functionality that allows you to blur, hue rotate colors, adjust
brightness and apply more such transformations over an image.

## Note

❗ No external modules are used except for [Junit](https://junit.org/junit5/) testing framework. It's not used when
building an app (see [build script](build-jar-unix.sh))

Pattern used: __Factory__ (see [TransformationFactory.java](src/main/java/transformations/TransformationFactory.java))

## Functionality

- [x] Edit and save edited image
- [x] Various transformations
- [x] Adjust brightness and contrast
- [x] Gamma correction
- [x] Rotate
- [x] HUE Rotate
- [x] Gaussian blur
- [x] Scale
- [ ] Crop
- [x] Presets

## Usage

```bash
java -jar ImageEditor.jar --in="image.jpg" --out="edited.jpeg" --scale=2 --blur=30 --hue=90
```

```bash
Climage - CLI Image Editor

usage:
  Option with value    --option=value
  Option without value --option

options:
  --brightness Adjust image brightness [Integer Multiplier]
  --preset Choose preset [String Preset name]
  --hue Hue rotate colors [Integer Degree]
  --crop Crop image to the given size [String X:Y W:H]
  --help Show help message and exit [None ]
  --scale Scale image by the multiplier [Float Multiplier]
  --saturation Change saturation of the image [Float Multiplier]
  --blur Apply Gaussian blur to the image [Integer Blur radius]
  --rotate Rotate image [Integer Angle]
  --out Path to save resulted image [String Path]
  --in Input image path [String Path]

presets:
  monochrome [Saturation + Brightness]
```

## Build

### Linux/MacOS/BSD

Run `./build-jar-unix.sh` in the root of the project. This will put jar file to the `out/jar/ImageEditor.jar`

#### Windows

> 🚧 In progress...

## Examples

> 🚧 In progress...
