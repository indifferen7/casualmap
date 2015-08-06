package se.cs.casualmap.api.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.cs.casualmap.api.map.Map;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;

public class MapWriter {

    private final Map map;

    private MapWriter(Map map) {
        this.map = checkNotNull(map);
    }

    public static MapWriter forMap(Map map) {
        return new MapWriter(map);
    }

    public void write(OutputStream stream) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);

        stream.write(json.getBytes(Charset.forName("UTF-8")));
    }



}
