package com.payment.service;

import com.payment.dto.AuthTransferDTO;
import com.payment.dto.RegisterDTO;
import com.payment.model.Transfer;
import com.payment.model.User;
import com.payment.repository.TransferRepository;
import com.payment.repository.UserRepository;
import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";

    @Transactional
    public void save(RegisterDTO registerDTO) {
        var senha = registerDTO.getPassword();
        registerDTO.setPassword(passwordEncoder.encode(senha));
        ModelMapper mapper = new ModelMapper();
        String cpfCnpj = registerDTO.getCpfCnpj();
        registerDTO.getWallet().setCpfCnpjUser(cpfCnpj);
        repository.save(mapper.map(registerDTO, User.class));
    }

    public User getById(Long id) throws NotFoundException {
        var user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND));
        return user;
    }

    public boolean update(Transfer transfer) throws NotFoundException {
        var userSender = getById(transfer.getIdSender());
        var userRecipient = getById(transfer.getIdRecipient());
        var authTransfer = requestAuth();

        if(userSender.getWallet().getValue() >=  transfer.getValue() && authTransfer.equals("Autorizado") ){
            var balenceSender = userSender.getWallet().getValue() - transfer.getValue();
            var balenceRecipient =  transfer.getValue() + userRecipient.getWallet().getValue();
            transfer.setUserSender(userSender.getCpfCnpj());
            transfer.setUserRecipient(userRecipient.getCpfCnpj());
            userSender.getWallet().setValue(balenceSender);
            userRecipient.getWallet().setValue(balenceRecipient);
            repository.save(userRecipient);
            repository.save(userSender);
            transferRepository.save(transfer);
            return true;
        }

        return false;
    }

    public String requestAuth(){

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
        ResponseEntity<AuthTransferDTO> response = restTemplate.getForEntity(url, AuthTransferDTO.class);
        String authTransfer = response.getBody().getMessage();
        return authTransfer;

    }

    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

}
