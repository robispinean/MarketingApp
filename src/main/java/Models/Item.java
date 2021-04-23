package Models;

public class Item {
    private String id;
    private String name;
    private String description;
    private double price;
    private String imagePath;

    public Item(String id, String name, String description, Double price, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return '"' + imagePath + '"';
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imagPath=" + imagePath +
                '}';
    }

}
