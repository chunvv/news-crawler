package com.chariot.shadow.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum TagType {

    START_UP(3, "Startup"),
    BUSINESS(4, "Business"),
    SCIENCE(5, "Science"),
    TECHNOLOGY(10, "Technology");

    private int id;
    private String name;

    public static TagType get(int id) {
        return Stream.of(values()).filter(value -> value.id == id).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static TagType get(String name) {
        return Stream.of(values()).filter(value -> value.name.equals(name)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
