package com.ced.app.service;

import com.ced.app.model.Commission;
import com.ced.app.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    public void createCommission(double pourcentage){
        commissionRepository.save(new Commission(pourcentage));
    }

    public Commission getCommission(){
        int index = commissionRepository.findAll().size() - 1;
        return commissionRepository.findAll().get(index);
    }
}
