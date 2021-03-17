package com.company.Builder;

public class Product {
    private String name;
    private int cost;
    public static class Builder {
        private String name;
        private int cost;
        public Builder setCost (int cost){
            this.cost = cost;
            return this;
        }
        public Builder setName (String name){
            this.name = name;
            return this;
        }
        public Product build(){
            Product pr = new Product();
            pr.cost = this.cost;
            pr.name = this.name;
            return pr;
        }
    }
}
