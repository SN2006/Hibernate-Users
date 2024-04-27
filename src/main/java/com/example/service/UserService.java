package com.example.service;

import com.example.entity.User;
import com.example.entity.UserMapper;
import com.example.exceptions.UserDataException;
import com.example.repository.impl.UserRepository;
import com.example.utils.Constants;
import com.example.utils.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {

    private final UserRepository userRepository = new UserRepository();
    private final UserMapper userMapper = new UserMapper();
    private final UserValidator userValidator = new UserValidator();

    public String create(Map<String, String> data){
        Map<String, String> errors = userValidator.validateUserData(data);
        if (!errors.isEmpty()){
            try {
                throw new UserDataException(Constants.INCORRECT_INPUT_MSG);
            } catch (UserDataException e) {
                return e.getErrors(errors);
            }
        }
        return userRepository.create(userMapper.mapData(data));
    }

    public String read(){
        Optional<List<User>> usersOptional = userRepository.read();

        if (usersOptional.isPresent()){
            List<User> users = usersOptional.get();

            return usersListToString(users);
        }
        return Constants.DATA_ABSENT_MSG;
    }

    public String update(Map<String, String> data){
        Map<String, String> errors = userValidator.validateUserData(data);
        if (!errors.isEmpty()){
            try {
                throw new UserDataException(Constants.INCORRECT_INPUT_MSG);
            } catch (UserDataException e) {
                return e.getErrors(errors);
            }
        }
        return userRepository.update(userMapper.mapData(data));
    }

    public String delete(Map<String, String> data){
        Map<String, String> errors = userValidator.validateUserData(data);
        if (!errors.isEmpty()){
            try{
                throw new UserDataException(Constants.INCORRECT_INPUT_MSG);
            } catch (UserDataException e) {
                return e.getErrors(errors);
            }
        }
        return userRepository.delete(userMapper.mapData(data).getId());
    }

    public String readUserById(Map<String, String> data){
        Map<String, String> errors = userValidator.validateUserData(data);
        if (!errors.isEmpty()){
            try{
                throw new UserDataException(Constants.INCORRECT_INPUT_MSG);
            } catch (UserDataException e) {
                return e.getErrors(errors);
            }
        }
        Optional<User> userOptional = userRepository.readById(userMapper.mapData(data).getId());
        if (userOptional.isEmpty()) return Constants.DATA_ABSENT_MSG;
        return userOptional.get().toString();
    }

    public String readUserByName(Map<String, String> data){
        Map<String, String> errors = userValidator.validateUserData(data);
        if (!errors.isEmpty()){
            try {
                throw new UserDataException(Constants.INCORRECT_INPUT_MSG);
            } catch (UserDataException e) {
                return e.getErrors(errors);
            }
        }
        Optional<List<User>> usersOptional = userRepository.readByName(userMapper.mapData(data).getName());
        if (usersOptional.isPresent()){
            List<User> users = usersOptional.get();

            return usersListToString(users);
        }
        return Constants.DATA_ABSENT_MSG;
    }

    public String deleteAllUsers(String agreement){
        switch (agreement){
            case "Y" -> {
                return userRepository.deleteAll();
            }
            case "N" -> {
                return Constants.DATA_DELETE_CANCEL_MSG;
            }
            default -> {
                return Constants.INCORRECT_OPTION_MSG;
            }
        }
    }

    private String usersListToString(List<User> users){
        if (!users.isEmpty()){
            AtomicInteger counter = new AtomicInteger();
            StringBuilder builder = new StringBuilder();
            users.forEach(user -> builder.append(counter.incrementAndGet())
                    .append(") ")
                    .append(user.toString())
                    .append("\n"));
            return builder.toString();
        }
        return Constants.DATA_ABSENT_MSG;
    }

}
