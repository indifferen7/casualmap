# casualmap
Casual map making. Because the world need more tile maps.

## Rationale
The rationale behind this project is pretty much non-existing. There are most probably many alternatives out there that can do just what casualmap does and more. 
Anyway, screw that, the following main points sums up what this tile map project is about.

**The casualmap project should**
* 1. offer a sweet and easy api to work with tile maps
* 2. include a way to generate such maps
* 3. support serialization and deserialization of maps

The idea right now is to package point 1 and 3 in an api module (casualmap-api) that the client can use to load and access the map via Java classes. The serialization format should be Json so other languages should not be difficult to support. Point number 2, the map generation stuff, is packaged in a generator module (casualmap-generator). 

So basically the casualmap-generator produce tile maps and deliver them in the format described by the casualmap-api. These maps could be serialized and saved for later or perhaps used directly in a game, making the game map random for each game. Another use case would be to generate maps until satisfied, serialize it and modifying it by hand. To verify the correctness of the map and to visually see it again, just load it up in the sample JavaFX application packaded in the casualmap-javafxsample module.
