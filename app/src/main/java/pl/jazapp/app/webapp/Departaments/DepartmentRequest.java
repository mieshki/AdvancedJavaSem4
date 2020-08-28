package pl.jazapp.app.webapp.Departaments;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DepartmentRequest {
    private String name;
    private Long id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return this.id; };
    public void setId(Long id) { this.id = id; }
}
