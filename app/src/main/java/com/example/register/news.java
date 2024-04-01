package com.example.register;

public class news {

    public news(){

    }
    String Category;
    String News;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getNews() {
        return News;
    }

    public void setNews(String news) {
        News = news;
    }

    public news(String category, String news) {
        Category = category;
        News = news;
    }
}
