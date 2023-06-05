package ru.hostco.pojo;

public class Indicator {
    private int id;
    private String name;
    private String unit;
    private int valueTypeId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public int getValueTypeId() {
        return valueTypeId;
    }
    public void setValueTypeId(int valueTypeId) {
        this.valueTypeId = valueTypeId;
    }

}
