package com.skypro.java.petshelterbot.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
/**
 * @author nadillustrator
 * Class of registration of dogs, which were taken by the owner. The database is filled in by a volunteer.
 */
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @Column(name = "pet_type")
    private String petType;
    @Column(name = "date_of_adoption")
    private LocalDate dateOfAdoption;


    public Pet(String name, String petType, LocalDate dateOfAdoption) {
        this.name = name;
        this.petType = petType;
        this.dateOfAdoption = dateOfAdoption;
    }

    public Pet() {
    }

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

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public LocalDate getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(LocalDate dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(petType, pet.petType) && Objects.equals(dateOfAdoption, pet.dateOfAdoption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, petType, dateOfAdoption);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", petType='" + petType + '\'' +
                ", dateOfAdoption=" + dateOfAdoption +
                '}';
    }
}
