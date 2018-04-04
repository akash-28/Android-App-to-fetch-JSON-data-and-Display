package com.recyclerview;

/**
 * Created by AKASH on 9/5/2017.
 */

public class ListItemModel {

    private  String head, desc, imageUrl , price, category;

    public ListItemModel(String head, String desc, String imageUrl, String price, String Category) {
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

