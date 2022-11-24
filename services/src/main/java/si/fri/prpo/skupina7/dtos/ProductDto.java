package si.fri.prpo.skupina7.dtos;

import si.fri.prpo.skupina7.Category;

public class ProductDto {
    private Integer productId;
    private String name;
    private String description;
    private Integer price;
    private Category category;

    public Integr getProductId() {return productId};
    public void setProductId(Integer productId) { this.productId = productId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Integer getPrice() {return price;}
    public void setPrice(Integer price) {this.price = price;}

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}
}
