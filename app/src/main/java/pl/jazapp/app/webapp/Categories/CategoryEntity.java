package pl.jazapp.app.webapp.Categories;

import pl.jazapp.app.webapp.Departaments.DepartmentEntity;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM CategoryEntity c")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="department_id")
    private DepartmentEntity department;

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

    public DepartmentEntity getDepartment_id() {
        return department;
    }

    public void setDepartment_id(DepartmentEntity department) {
        this.department = department;
    }
}

