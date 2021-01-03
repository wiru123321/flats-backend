package com.projekt.inzynierka.responses.User;


import com.projekt.inzynierka.model.User;
import com.projekt.inzynierka.responses.FlatsDTO;
import com.projekt.inzynierka.responses.RoleDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private FlatsDTO flatsDTO;
    private RoleDTO roleDTO;

    public UserDTO(final User user, final RoleDTO roleDTO,final FlatsDTO flatsDTO) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.flatsDTO = flatsDTO;
        this.roleDTO = roleDTO;
    }

    public UserDTO(final Long id,final String login, final String email, final String name, final String surname, final String phoneNumber, final RoleDTO roleDTO,final FlatsDTO flatsDTO) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.roleDTO = roleDTO;
        this.flatsDTO = flatsDTO;
    }

    public UserDTO() {
    }
}