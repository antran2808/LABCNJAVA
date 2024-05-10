package com.example.Lab65;

public class DataSourceConfig {
    private String id;
    private String name;
    private String factory;
    private String idea;

    @Override
    public String toString() {
        return "My Information [ID = "  + id + ", FullName = " + name + ", Factory = " + factory + ", IDEA = " + idea+ "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}