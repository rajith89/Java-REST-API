package com.testbranch.service.impl;

import com.testbranch.constants.Subscription;
import com.testbranch.model.Account;
import com.testbranch.model.Role;
import com.testbranch.model.User;
import com.testbranch.repository.RoleRepository;
import com.testbranch.repository.UserRepository;
import com.testbranch.web.error.UserAlreadyExistException;
import com.testbranch.web.error.UserNotFoundException;
import com.testbranch.web.rest.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements com.testbranch.service.UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private HttpServletRequest request;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    public void createUserAccount(UserDto accountDto) throws UserAlreadyExistException {
        final User user = new User();
        try {
            if (userRepository.findByEmail(accountDto.getEmail()).isPresent()) {
                throw new UserAlreadyExistException("Registration failed: User already exists with email " + accountDto.getEmail());
            }

            user.setEmail(accountDto.getEmail());
            user.setAccount(new Account());
            user.setPasswordEnc(bCryptPasswordEncoder.encode(accountDto.getPassword()));
            user.setActive(1);

            Account newAcc = new Account();
            newAcc.setName(accountDto.getEmail());
            newAcc.setSubscription(Subscription.FREE);
            user.setAccount(newAcc);
            Role userRole = new Role();
            userRole.setId(accountDto.getRoleId());
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userRepository.save(user);

       } catch (UserAlreadyExistException e) {
            throw new UserAlreadyExistException(e.getMessage());
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public List<UserDto> getAllActiveUsers(int status){

        List<User> userList = userRepository.findAllByActive(status);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userList) {
            if (!userList.isEmpty()){
                UserDto userDto = UserDto
                        .builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .build();
                userDtos.add(userDto);
            }
        }
        return userDtos;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }
        User usr = user.get();
        usr.setPasswordEnc(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(usr);
        return userDto;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }
        User usr = user.get();
        usr.setActive(0);
        userRepository.save(usr);
    }

    @Override
    public User findUserByEmail(String email) {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        return userRepository.findByEmail(email).get();
    }

    @Override
    public UserDto findUserById(long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }
        User usr = user.get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(usr, userDto);
        return userDto;
    }


    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


}
