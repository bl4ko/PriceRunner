package si.fri.prpo.skupina7.dtos;

import si.fri.prpo.skupina7.Category;
import si.fri.prpo.skupina7.ProductStorePrice;

import java.util.List;

public class ProductDto {
    private Integer productId;
    private String name;
    private String description;
    private Category category;
    private List<ProductStorePrice> productStorePrices;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductStorePrice> getProductStorePrices() {
        return productStorePrices;
    }

    public void setProductStorePrices(List<ProductStorePrice> productStorePrices) {
        this.productStorePrices = productStorePrices;
    }
}
