package com.example.demo3.models;

public class ShowCartProduct
{
    private int productid;
    private String productname;
    private String productdescription;
    private String brand;
    private double mrp;
    private String imagename = "/images/product_01.png";
    private int quantity;
    private int qtyinstock;

    public ShowCartProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQtyinstock() {
        return qtyinstock;
    }

    public void setQtyinstock(int qtyinstock) {
        this.qtyinstock = qtyinstock;
    }

    public ShowCartProduct(int productid, String productname, String productdescription, String brand, double mrp, String imagename, int quantity, int qtyinstock) {
        this.productid = productid;
        this.productname = productname;
        this.productdescription = productdescription;
        this.brand = brand;
        this.mrp = mrp;
        this.imagename = imagename;
        this.quantity = quantity;
        this.qtyinstock = qtyinstock;
    }

    @Override
    public String toString() {
        return "ShowCartProduct{" +
                "productid=" + productid +
                ", productname='" + productname + '\'' +
                ", productdescription='" + productdescription + '\'' +
                ", brand='" + brand + '\'' +
                ", mrp=" + mrp +
                ", imagename='" + imagename + '\'' +
                ", quantity=" + quantity +
                ", qtyinstock=" + qtyinstock +
                '}';
    }
}
