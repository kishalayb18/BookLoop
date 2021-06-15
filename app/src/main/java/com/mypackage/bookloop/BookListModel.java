package com.mypackage.bookloop;

public class BookListModel
{

    private String sellerName,bookName,publisherName,authorName,bookSem,bookDescription,bookPrice;
    //private String ;

    public BookListModel() {
    }

    public BookListModel(String sellerName, String bookName) {
        this.sellerName = sellerName;
        this.bookName = bookName;
    }

    public BookListModel(String sellerName, String bookName, String publisherName, String authorName, String bookSem, String bookDescription, String bookPrice) {
        this.sellerName = sellerName;
        this.bookName = bookName;
        this.publisherName = publisherName;
        this.authorName = authorName;
        this.bookSem = bookSem;
        this.bookDescription = bookDescription;
        this.bookPrice = bookPrice;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookSem() {
        return bookSem;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookSem(String bookSem) {
        this.bookSem = bookSem;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
