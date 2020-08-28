package pl.jazapp.app.webapp.Departaments;

import pl.jazapp.app.webapp.Categories.CategoryEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@NamedQuery(name="Department.findAll", query="SELECT d FROM DepartmentEntity d")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<CategoryEntity> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
