package models.cell;

import models.Color;
import models.Type;

public abstract class Cell {
    private int id;
    private String name;
    private Type type;
    private String icon;

    private Color color;
    private String description;

    public Cell(){

    }

    public Cell(Type type){
        this.type = type;
    }

    public Cell(int id, String name, Type type, String icon, Color color, String description){
        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
}
