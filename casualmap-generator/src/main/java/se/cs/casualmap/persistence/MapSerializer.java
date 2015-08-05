package se.cs.casualmap.persistence;

import com.google.gson.Gson;
import se.cs.casualmap.api.map.Map;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;

public class MapSerializer {

    private final Map map;

    private MapSerializer(Map map) {
        this.map = checkNotNull(map);
    }

    public static MapSerializer target(Map map) {
        return new MapSerializer(map);
    }

    public void output(OutputStream stream) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(map);

        stream.write(json.getBytes(Charset.forName("UTF-8")));
    }



}
