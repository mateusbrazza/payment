package com.payment.service;

import com.payment.model.User;
import com.payment.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;


    public void save(User user) {
        ModelMapper mapper = new ModelMapper();
        repository.save(mapper.map(user, User.class));
    }

//    public void save(RestaurantEntity restaurant) {
//        ModelMapper mapper = new ModelMapper();
//        restaurantRepository.save(mapper.map(restaurant, RestaurantEntity.class));
//    }
}
