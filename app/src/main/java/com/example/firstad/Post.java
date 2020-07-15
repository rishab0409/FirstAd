package com.example.firstad;

public class Post {
    String title;
    String description;
    String date;
    String company_name;
    String img_url;


    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", company_name='" + company_name + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }



    public Post() {
    }

//    @Override
//    public String toString() {
//        return "Post{" +
//                "title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", date='" + date + '\'' +
//                ", company_name='" + company_name + '\'' +
//                '}';
//    }
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
