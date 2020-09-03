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
    private String photo1;
    private String photo2;
    private String photo3;

    private String param1Name;
    private String param1Value;

    private String param2Name;
    private String param2Value;

    private String param3Name;
    private String param3Value;

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

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getParam1Name() {
        return param1Name;
    }

    public void setParam1Name(String param1Name) {
        this.param1Name = param1Name;
    }

    public String getParam1Value() {
        return param1Value;
    }

    public void setParam1Value(String param1Value) {
        this.param1Value = param1Value;
    }

    public String getParam2Name() {
        return param2Name;
    }

    public void setParam2Name(String param2Name) {
        this.param2Name = param2Name;
    }

    public String getParam2Value() {
        return param2Value;
    }

    public void setParam2Value(String param2Value) {
        this.param2Value = param2Value;
    }

    public String getParam3Name() {
        return param3Name;
    }

    public void setParam3Name(String param3Name) {
        this.param3Name = param3Name;
    }

    public String getParam3Value() {
        return param3Value;
    }

    public void setParam3Value(String param3Value) {
        this.param3Value = param3Value;
    }
}
