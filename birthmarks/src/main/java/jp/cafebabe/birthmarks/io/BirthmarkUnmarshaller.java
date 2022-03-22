package jp.cafebabe.birthmarks.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.elements.ElementBuilder;
import jp.cafebabe.birthmarks.entities.impl.Builder;
import jp.cafebabe.birthmarks.utils.JsonUtil;

import java.net.URI;

class BirthmarkUnmarshaller {
    public Birthmark unmarshal(JsonObject object) throws JsonSyntaxException {
        var builder = parseMetadata(object.getAsJsonObject("metadata"), new Builder());
        var containerType = parseContainerType(object.getAsJsonPrimitive("container").getAsString());
        var elements = object.getAsJsonArray("elements");
        var values = JsonUtil.stream(elements)
                .map(e -> parseElement(e, containerType));
        return builder.build(values, containerType);
    }

    private Element parseElement(JsonElement element, ContainerType type) {
        return switch(type) {
            case List, Set -> parseElement(element);
            case Vector -> parseVectorElement(element.getAsString());
            case Graph -> null;
        };
    }

    private Element parseElement(JsonElement element) {
        if(element.isJsonPrimitive()) {
            var primitive = element.getAsJsonPrimitive();
            if(primitive.isNumber())
                return ElementBuilder.build(primitive.getAsNumber());
            return ElementBuilder.build(primitive.getAsString());
        }
        throw new IllegalArgumentException(element + ": invalid");
    }

    private Element parseVectorElement(String element) {
        var items = element.split("=");
        if(items.length != 2)
            throw new IllegalArgumentException(element + ": invalid vector element format");
        return ElementBuilder.build(items[0], Long.valueOf(items[1]));
    }

    private ContainerType parseContainerType(String type) {
        return switch(type) {
            case "list" -> ContainerType.List;
            case "set" -> ContainerType.Set;
            case "graph" -> ContainerType.Graph;
            case "vector" -> ContainerType.Vector;
            default -> throw new IllegalArgumentException(type + ": unknown container type");
        };
    }

    private Builder parseMetadata(JsonObject object, Builder builder) {
        String name = object.getAsJsonPrimitive("name").getAsString();
        String uri = object.getAsJsonPrimitive("location").getAsString();
        String type = object.getAsJsonPrimitive("type").getAsString();
        return builder.metadata(name, URI.create(uri), type);
    }
}
