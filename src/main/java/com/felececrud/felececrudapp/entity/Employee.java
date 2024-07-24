package com.felececrud.felececrudapp.entity;

import com.felececrud.felececrudapp.entity.OtherInformation;
import com.felececrud.felececrudapp.entity.PersonalInformation;
import com.felececrud.felececrudapp.entity.Project;
import com.felececrud.felececrudapp.enums.ContractType;
import com.felececrud.felececrudapp.enums.Level;
import com.felececrud.felececrudapp.enums.Team;
import com.felececrud.felececrudapp.enums.WorkType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "Name can not be empty")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Enumerated(EnumType.STRING)
    @Column(name = "levell")
    private Level level;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_type")
    private WorkType workType;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type")
    private ContractType contractType;

    @Enumerated(EnumType.STRING)
    @Column(name = "team")
    private Team team;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_information_id", referencedColumnName = "id")
    private PersonalInformation personalInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "other_information_id", referencedColumnName = "id")
    private OtherInformation otherInformation;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public Employee(Long employeeId) {
        this.id = employeeId;
    }
}
