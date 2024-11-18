package com.felantry.tcentury;

import org.bukkit.Material;

public class Century {

    private String name;
    private Material resource;
    private int amount;
    private String nextCentury;

    public Century(String name, Material resource, int amount, String nextCentury) {
        this.name = name;
        this.resource = resource;
        this.amount = amount;
        this.nextCentury = nextCentury;
    }

    public String getName() {
        return name;
    }

    public Material getResource() {
        return resource;
    }

    public int getAmount() {
        return amount;
    }

    public String getNextCentury() {
        return nextCentury;
    }
}