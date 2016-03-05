package com.rockets12.lunchcast.adapter;

import java.io.Serializable;

/**
 * Created by Stevan Kovacevic on 3/4/2016.
 */
public class OrderDetail implements Serializable {

    private String title;
    private String subTitle;
    private int quantity;
    private int price;

    public OrderDetail(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void addSubTitle(String add) {
        subTitle = subTitle.concat(", " + add);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void addTotalPrice(int price) {
        this.price += price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        return !(title != null ? !title.equals(that.title) : that.title != null);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
