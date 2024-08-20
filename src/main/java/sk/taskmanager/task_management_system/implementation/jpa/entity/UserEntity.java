package sk.taskmanager.task_management_system.implementation.jpa.entity;

import jakarta.persistence.*;

@Entity(name="user")
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
