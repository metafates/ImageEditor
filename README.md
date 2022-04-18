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

❗ No external modules are used except for [Junit](https://junit.org/junit5/) testing framework

## Functionality

- [x] Edit and save edited image
- [x] Various transformations
- [x] Adjust brightness and contrast
- [x] Gamma correction
- [x] Rotate
- [x] HUE Rotate
- [x] Gaussian blur

## Usage

```bash
climage --in="image.jpg" --out="edited.jpeg" --scale=2 --blur=30 --hue=90
```

```bash
options:
  --brightness Adjust image brightness [Integer]
  --hue Hue rotate colors [Integer]
  --crop Crop image to the given size [String]
  --help Show help message and exit [None]
  --scale Scale image by the multiplier [Decimal]
  --saturation Change saturation of the image [Decimal]
  --blur Apply blur to them image [Integer]
  --rotate Rotate image [Integer]
  --out Path to save resulted image [String]
  --in Input image path [String]
```

## Build

### Linux/MacOS/BSD

Run `./build-jar-unix.sh` in the root of the project.
This will put jar file to the `out/jar/ImageEditor.jar`

#### Windows

> 🚧 In progress...

## Examples

> 🚧 In progress...
