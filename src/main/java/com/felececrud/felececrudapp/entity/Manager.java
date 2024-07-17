package com.felececrud.felececrudapp.entity;

import com.felececrud.felececrudapp.enums.ContractType;
import com.felececrud.felececrudapp.enums.Level;
import com.felececrud.felececrudapp.enums.Team;
import com.felececrud.felececrudapp.enums.WorkType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manager")
public class Manager{
    // define our fields

    // define constructors

    // define getter setters

    // define toString

    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "levell")
    private Level level;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "work_type")
    private WorkType workType;

    @Column(name = "contract_type")
    private ContractType contractType;

    @Column(name = "team")
    private Team team;

    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_informations_id")
    private PersonalInformation personalInformation;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "other_informations_id")
    private OtherInformation otherInformation;

    @Column(name = "project_id")
    private int projectId;

    @OneToMany(mappedBy = "manager",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH} )
    private List<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "manager_project",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    // convenience method

    public void add(Employee tempEmployee){
        if (employees == null){
            employees = new ArrayList<>();

        }
        employees.add(tempEmployee);
        tempEmployee.setManager(this);
    }
    public Manager(){

    }

    public Manager(String firstName, String lastName, Level level, String phoneNumber, String email, String birthDate, WorkType workType, ContractType contractType, Team team, String startDate, String endDate, int personalInformationId, int otherInformationId, int projectId, List<Employee> employees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.workType = workType;
        this.contractType = contractType;
        this.team = team;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public OtherInformation getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(OtherInformation otherInformation) {
        this.otherInformation = otherInformation;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", level=" + level +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", workType=" + workType +
                ", contractType=" + contractType +
                ", team=" + team +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", projectId=" + projectId +
                ", employees=" + employees +
                '}';
    }
}
