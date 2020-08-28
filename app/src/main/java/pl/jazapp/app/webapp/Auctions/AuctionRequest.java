package pl.jazapp.app.webapp.Auctions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class AuctionRequest {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long department_id;
    private Long category_id;
    private Long owner_id;
    private Long version;
    private String photos_urls;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getPhotos_urls() {
        return photos_urls;
    }

    public void setPhotos_urls(String photos_urls) {
        this.photos_urls = photos_urls;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
}
