package si.fri.prpo.skupina7.dtos;

public class ProductStorePriceDto {
    private String storeName;
    private Integer price;
    
    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
