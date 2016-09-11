package cnam.glg204.quiz.server.service.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import cnam.glg204.quiz.common.config.Constants;
import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.CreateException;
import cnam.glg204.quiz.common.exceptions.UpdateException;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;

@Service("userService")
@Transactional
@Rollback(true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public UserServiceImpl() {

    }

    @Override
    public UserDto findUser(long id) {
        User user = userDao.find(id);
        return userToUserDto(user);
    }

    @Override
    public void createAccount(UserDto userDto) throws CheckException, CreateException {
      
        
        
        User user = new User();
        user.setAccountType(AccountType.AUDITOR);
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());

        user.setLastName(userDto.getLastName());
        if (userDto.getBirthDay() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT2);
            try {
                user.setBirthDay(formatter.parse(userDto.getBirthDay()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        user.setPresentation(userDto.getPresentation());
        user.checkData();

        if (userDao.findByMail(userDto.getEmail()) != null) {
            throw new CreateException("An user with the  mail " + userDto.getEmail() + " already exist in the database");
        }

        userDao.save(user);
        userDto.setAccountType(user.getAccountType());
        userDto.setId(user.getId());
        System.out.println("id " + user.getId());
    }

    @Override
    public void updateProfil(UserDto userDto) throws CheckException , UpdateException{
        
        User user = userDao.find(userDto.getId());
        User user2 = userDao.findByMail(userDto.getEmail()) ;
        
        if ( user2!= null &&  !user.equals (user2 ) && user2.getEmail().equals(user2.getEmail() ) )  
                throw new  UpdateException("An user with the  mail " + userDto.getEmail() + " already exist in the database");
       
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (userDto.getBirthDay() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT2);
            if (userDto.getBirthDay() != null) {
                try {
                    user.setBirthDay(formatter.parse(userDto.getBirthDay()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    
        user.setPresentation(userDto.getPresentation());
        user.checkData();
        
    

        userDao.save(user);
    }

    @Override
    public void deleteAccount(long id) {
        User user = userDao.find(id);
        userDao.delete(user);
    }

    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT2);
        if (user.getBirthDay() != null) {
            userDto.setBirthDay(formatter.format(user.getBirthDay()));
        }
        userDto.setPresentation(user.getPresentation());
        userDto.setAccountType(user.getAccountType());
        return userDto;
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setAccountType(userDto.getAccountType());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT2);
        if (userDto.getBirthDay() != null) {
            if (userDto.getBirthDay() != null) {
                try {
                    user.setBirthDay(formatter.parse(userDto.getBirthDay()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        user.setPresentation(userDto.getPresentation());
        return user;
    }

}
