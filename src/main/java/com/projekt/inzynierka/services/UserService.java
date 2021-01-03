package com.projekt.inzynierka.services;

import com.projekt.inzynierka.model.Flats;
import com.projekt.inzynierka.model.User;
import com.projekt.inzynierka.model.UserAccount;
import com.projekt.inzynierka.repositories.UserRepository;
import com.projekt.inzynierka.responses.FlatsDTO;
import com.projekt.inzynierka.responses.User.UserCreation;
import com.projekt.inzynierka.responses.User.UserDTO;
import com.projekt.inzynierka.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.projekt.inzynierka.responses.User.UserUpdate;


@Service
public class UserService implements UserServiceInterface {

    final UserRepository userRepository;
    final RoleService roleService;
    final FlatsService flatsService;
    final PasswordEncoder bCryptPasswordEncoder;
    final FaultsService faultsService;

    @Autowired
    public UserService(final UserRepository userRepository, final RoleService roleService, FlatsService flatsService, final PasswordEncoder bCryptPasswordEncoder, FaultsService faultsService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.flatsService = flatsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.faultsService = faultsService;
    }


    @Override
    public Long updateUserInDB(String login) {
        return null;
    }

    @Override
    public Long updateUserInDB(final String login, final UserAccount userAccount) {
        final User oldUser = this.getEntityByLoginAndisActive(login, true);
        oldUser.getFlats().getUserAccount().setRentCost(userAccount.getRentCost());
        oldUser.getFlats().getUserAccount().setRubbishCost(userAccount.getRubbishCost());
        oldUser.getFlats().getUserAccount().setWaterCost(userAccount.getWaterCost());
        oldUser.getFlats().getUserAccount().setUserRentPayment(userAccount.getUserRentPayment());
        oldUser.getFlats().getUserAccount().setUserRubbishPayment(userAccount.getUserRubbishPayment());
        oldUser.getFlats().getUserAccount().setUserWaterCost(userAccount.getUserWaterCost());
        oldUser.getFlats().getUserAccount().setPaymentDate(userAccount.getPaymentDate());
        oldUser.getFlats().getUserAccount().setUserPaymentDate(userAccount.getUserPaymentDate());
        oldUser.getFlats().getUserAccount().setIsActive(userAccount.getIsActive());
        return userRepository.save(oldUser).getId();
    }

    @Override
    public Long updateUserFlatsInfoInDB(final String login, final Flats flats) {
        final User oldUser = this.getEntityByLoginAndisActive(login, true);
        oldUser.getFlats().setArea(flats.getArea());
        oldUser.getFlats().setFlor(flats.getFlor());
        oldUser.getFlats().setPeopleInFlat(flats.getPeopleInFlat());
        oldUser.getFlats().setRooms(flats.getRooms());
        oldUser.getFlats().setIsBalcony(flats.getIsBalcony());
        oldUser.getFlats().setId(flats.getId());
        return userRepository.save(oldUser).getId();
    }
    @Override
    public Long updateUserInfoInDB(final String login, final User user) {
        final User oldUser = this.getEntityByLoginAndisActive(login, true);
        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setId(user.getId());
        return userRepository.save(oldUser).getId();
    }

    @Override
    public Long addEntityToDB(final User user) {
        return userRepository.save(user).getId();
    }

//    @Override //NO PASSWORD CHANGE
//    public Long updateUserInDB(final String login, final UserUpdate userUpdate) {
//        final User oldUser = this.getEntityByLoginAndisActive(login, true);
//        oldUser.setName(userUpdate.getName());
//        oldUser.setSurname(userUpdate.getSurname());
//        oldUser.setEmail(userUpdate.getEmail());
//        oldUser.setPhoneNumber(userUpdate.getPhoneNumber());
//        return userRepository.save(oldUser).getId();
//    }

    @Override
    public Long setUserIsNotActive(final String login) {
//        final Flats flats = this.getEntityByLogin(login).getFlats();
//        this.getEntityByLogin(login).setFlats(null);
//        flatsService.deleteFlat(flats);
        final User user = this.getEntityByLogin(login);
        user.setIsActive(false);
        return userRepository.save(user).getId();
    }

    @Override
    public void changeEmail(final User user, final String email) {
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void changePhoneNumber(final User user, final String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }


    @Override
    public boolean changePassword(final User user, final String password) {
        final String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_.@#$%^&+=])(?=\\S+$).{8,}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(password);
        System.out.println(matcher.matches());
        System.out.println(password);
        if (matcher.matches()) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        }
        return matcher.matches();
    }

    @Override
    public boolean checkPassword(final String given, final String actual) {
        return bCryptPasswordEncoder.matches(given, actual);
    }

    @Override
    public boolean checkEmail(final String email) {
        final String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean checkPhoneNumber(final String phoneNumber) {

        final String regex = "[5-9][0-9]{8}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public Boolean checkIfUserWithLoginExists(final String login) {
        return userRepository.existsByLogin(login);
    }

    @Override //NO PASSWORD MAPPING
    public User mapRestModel(final Long id, final UserDTO userDTO, Long flatId) {
        return new User(id, userDTO, roleService.getEntityByRoleName(userDTO.getRoleDTO().getName()),flatsService.getEntityById(flatId));
    }

    @Override
    public User mapCreationModel(final Long id, final UserCreation userCreation, final Long flatId) {
        return new User(id, userCreation, roleService.getEntityByRoleName(userCreation.getRoleDTO().getName()),flatsService.getEntityById(flatId));
    }

    @Override
    public User getEntityByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getEntityByLoginAndisActive(final String login, final Boolean isActive) {
        return userRepository.findByLoginAndIsActive(login, isActive);
    }

    @Override
    public User getEntityByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User mapCreationModel(Long id, UserCreation userCreation) {
        return null;
    }


    @Override
    public UserDTO getDTOByLogin(final String login) {
        final User user = userRepository.findByLogin(login);
        return new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()),new FlatsDTO(user.getFlats()));
    }

    @Override
    public UserDTO getDTOByEmail(final String email) {
        final User user = userRepository.findByEmail(email);
        return new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()),new FlatsDTO(user.getFlats()));
    }

    @Override
    public List<UserDTO> getAllDTOs() {
        final ArrayList<User> userArrayList = new ArrayList<>();
        userRepository.findAll().forEach(userArrayList::add);
        return this.mapRestList(userArrayList);
    }

    @Override
    public List<UserDTO> getAllActiveUserDTOs() {
        final ArrayList<User> userArrayList = new ArrayList<>(userRepository.findAllByIsActiveAndAndRole_Name(true,"RESIDENT"));
        return this.mapRestList(userArrayList);
    }


    private List<UserDTO> mapRestList(final List<User> userArrayList) {
        final ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        userArrayList.forEach((user) -> {
            final UserDTO userDTO = new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()),new FlatsDTO(user.getFlats()));
            userDTOArrayList.add(userDTO);
        });
        return userDTOArrayList;
    }
}
