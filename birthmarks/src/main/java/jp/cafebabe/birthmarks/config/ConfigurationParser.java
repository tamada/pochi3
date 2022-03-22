package jp.cafebabe.birthmarks.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jp.cafebabe.birthmarks.io.Parser;
import jp.cafebabe.birthmarks.utils.JsonUtil;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurationParser implements Parser<Configuration> {
    public Configuration parse(JsonElement element) {
        return constructConfig(element.getAsJsonObject());
    }

    private Configuration constructConfig(JsonObject object) {
        var props = readProperties(object.getAsJsonObject("properties"));
        var rules = parseRules(object.getAsJsonArray("rules"));
        return new Configuration(rules, props);
    }

    private Map<String, String> readProperties(JsonObject object) {
        return object.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getValue(e.getValue()), (a, b) -> b));
    }

    private String getValue(JsonElement element) {
        return element.getAsString();
    }

    private Rules parseRules(JsonArray array) {
        return new Rules(JsonUtil.stream(array)
                .map(element -> parseRule(element.getAsJsonObject())));
    }

    private Rule parseRule(JsonObject object) {
        var type = object.getAsJsonPrimitive("type").getAsString();
        var pattern = object.getAsJsonPrimitive("pattern").getAsString();
        return new Rule(type, pattern);
    }

    public static Configuration defaultConfiguration() throws IOException {
        return new ConfigurationParser().parse(ConfigurationParser.class
                .getResource("/resources/config.json"));
    }
}