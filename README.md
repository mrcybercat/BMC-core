# BMC

BMC (Basic Multimedia Components) is a java application providing different features to interact with multimedia.
It can be used as a stand alone command-line application. Current features include JPEG compression and metric analysis of images and videos,

## Installation (Docker)

You can run BCM Core using Docker:

```sh
docker build -t bmc-core .
docker run --rm -v "absolute/path/to/data:/app/data" bmc-core <command> [options]
```

Replace `<command> [options]` with the desired command and parameters.

## Usage

```sh
java -jar bmc.jar <command> [options]
```

## Commands and Options

### `compress`
Compress an input file using the standard JPEG algorithm.

#### Options:
- `-i <input>`  : Specifies the input file.
- `-o <output>` : Specifies the output file.

#### Example:
```sh
java -jar bmc.jar compress -i input.jpg -o output.jpg
```

---

### `metric`
Perform metric analysis on images or videos.

#### Options:
- `-i <input>`  : Specifies the original file.
- `-o <output>` : Specifies the altered file.
- `-m <mode>`   : Specifies the metric mode (required).
    - `image`              : Performs an image metric analysis.
    - `video-sequential`   : Performs a sequential video metric analysis.
    - `video-spacial`      : Performs a spatial video metric analysis.

#### Examples:
- Image metric analysis:
```sh
java -jar bmc.jar metric -i original.png -o altred.jpg -m image
```

- Sequential video metric analysis:
```sh
java -jar bmc.jar metric -i original.mp4 -o altred.mp4 -m video-sequential
```

- Spatial video metric analysis:
```sh
java -jar bmc.jar metric -i original.mp4 -o altred.mp4 -m video-spacial
```
---

