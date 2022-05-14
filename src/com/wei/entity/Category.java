package com.wei.entity;

public class Category {
    private int cat_id;
    private String cat_name;
    private int cat_sal_id;

    @Override
    public String toString() {
        return "Category{" +
                "cat_id=" + cat_id +
                ", cat_name='" + cat_name + '\'' +
                ", cat_sal_id=" + cat_sal_id +
                '}';
    }

    public Category() {
    }

    public Category(String cat_name, int cat_sal_id) {
        this.cat_name = cat_name;
        this.cat_sal_id = cat_sal_id;
    }

    public Category(int cat_id, String cat_name, int cat_sal_id) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_sal_id = cat_sal_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getCat_sal_id() {
        return cat_sal_id;
    }

    public void setCat_sal_id(int cat_sal_id) {
        this.cat_sal_id = cat_sal_id;
    }
}
