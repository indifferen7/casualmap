# casualmap
Casual map making. Because the world need more tile maps. And dungeon crawlers.

## Rationale
The rationale behind this project is pretty much non-existing. There are most probably many alternatives out there that can do just what casualmap does and more. 
Anyway, screw that, the following main points sums up what this tile map project is about.

**The casualmap project should** (at some point)
* support the generation of tile maps (1)
* offer a sweet and easy api to query a tile map (2)
* include mechanisms for map serialization/deserialization (3)
 
## Requirements
* Java 8
* Maven 3

## So how can I use the code?
There are several ways to use the code. The first thing to do is to check out the code and run mvn install from the base directory. Then you can do any of the following.

### a. Generate maps from command line
This is easy peasy. From the base directory, travel to casualmap-generator/target. From there you can run the following command to generate a map:

$ java -jar casualmap-generator-console.jar

The above command will generate a map and output it in JSON format directoy in the console. If you want to send it to a file instead you can add the -f option to your command, plus the desired file name, like so:

$ java -jar casualmap-generator-console.jar -f map.json

### b. Use the casualmap-api
If you have a JSON serialized map serialized to Json and want to make use of the map in Java you can add a dependency to the casualmap-api module to your code. The classes in this module allows you to load serialized maps into a nice API to query the data structure in all sorts of ways. Try it out! Naturally, as the serialized format is Json, the map can be used in pretty much any programming lanugage out there, so you're definately not limited to Java.

### c. Try out the JavaFX ui
Right now the JavaFX ui is only for show but it looks cool. The plan is to make it more competent in terms of settings and input/output options. Right now all you can do is to generate maps to see what they look like. The following image is a screen shot from the primitive JavaFX sample module (as of 2015-08-01) that exists only to demonstrate how the map generation works. This will have to do for now.

![Super early version of the gui](https://dl.dropboxusercontent.com/u/404130/example.png)

