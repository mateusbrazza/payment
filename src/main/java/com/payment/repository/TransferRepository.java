package com.payment.repository;

import com.payment.model.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository  extends CrudRepository<Transfer,Integer> {
}
