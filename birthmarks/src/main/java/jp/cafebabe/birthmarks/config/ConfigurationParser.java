package jp.cafebabe.birthmarks.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.io.Parser;
import jp.cafebabe.birthmarks.utils.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurationParser implements Parser<Configuration> {
    public Configuration parse(JsonElement element, Map<String, String> properties) {
        return constructConfig(element.getAsJsonObject(), properties);
    }

    public Configuration parse(JsonElement element) {
        return parse(element, new HashMap<>());
    }

    public Configuration parse(Reader in, Map<String, String> properties) throws IOException {
        return Try.of(() -> parse(JsonParser.parseReader(in), properties))
                .getOrElseThrow(t -> new IOException(t));
    }

    public Configuration parse(InputStream in, Map<String, String> properties) throws IOException {
        return parse(new InputStreamReader(in), properties);
    }

    public Configuration parse(URL url, Map<String, String> properties) throws IOException {
        return parse(url.openStream(), properties);
    }

    private Configuration constructConfig(JsonObject object, Map<String, String> properties) {
        var props = readProperties(object.getAsJsonObject("properties"));
        var newProps = new HashMap<String, String>();
        newProps.putAll(props);
        newProps.putAll(properties);
        var rules = parseRules(object.getAsJsonArray("rules"));
        return new Configuration(rules, newProps);
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

    public static Configuration defaultConfig() throws IOException {
        return new ConfigurationParser().parse(Configuration.class
                .getResource("/resources/config.json"));
    }

    public static Configuration defaultConfig(Map<String, String> properties) throws IOException {
        return new ConfigurationParser().parse(Configuration.class
                .getResource("/resources/config.json"), properties);
    }
}