package ru.agcon.authorization_service.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @PreAuthorize("hasRole('SELLER')")
    public void doAdminStuff() {
        System.out.println("Only admin here");
    }
}