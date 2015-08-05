package se.cs.casualmap.persistence;

import com.google.gson.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import se.cs.casualmap.api.map.Map;

public class MapRepositoryFS {
    private Path path = Paths.get("test.json");
    private Charset charset = StandardCharsets.UTF_8;

    public void save(Map map)  {
        Gson gson = new Gson();
        String json = gson.toJson(map);
        try {
            Files.write(this.path, json.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map fetch() throws IOException {
            List<String> maps = Files.readAllLines(this.path, this.charset);
            Gson gson = new Gson();
            return gson.fromJson(maps.get(0), Map.class);
    }

    public void cleanUp() {
        try {
            Files.delete(this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









