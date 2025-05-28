package com.ecommerce.services.oauth;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.LoginResponseDTO;
import com.ecommerce.exceptionhandler.UnauthorizedException;
import com.ecommerce.model.User;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.services.cart.CartService;
import com.ecommerce.services.email.EmailService;
import com.ecommerce.util.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleOAuthService {
	
    @Value("${google.clientId}")
    private String googleClientId;

    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final CartService cartService;
    private final EmailService emailService;

    public GoogleOAuthService(UserRepo userRepo, JwtUtil jwtUtil, CartService cartService, EmailService emailService) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.cartService = cartService;
        this.emailService = emailService;
    }
    public LoginResponseDTO loginOrRegisterWithGoogle(String idTokenString) {
        try {
        	GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
        	        .setAudience(Collections.singletonList(googleClientId))
        	        .build();
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                throw new UnauthorizedException("Invalid Google ID Token");
            }

            Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            User user = userRepo.findByEmail(email);
            boolean isNewUser = false;

            if (user == null) {
                user = new User(name, email, "", 0L, "", false, false, false,false);
                userRepo.save(user);
                cartService.addCartId(user.getUserId());
                emailService.sendAccountCreatedEmail(email, name);
                List<User> superAdmins = userRepo.findSuperAdmins();
                List<String> superAdminEmails = superAdmins.stream()
                                                           .map(User::getEmail)
                                                           .collect(Collectors.toList());
                emailService.intimateSuperAdmins(superAdminEmails, user);
                isNewUser = true;
            }

            String role;
            if (user.isMainAdmin()) {
                role = "ROLE_SUPER_ADMIN";
            } else if (user.isProductAdmin()) {
                role = "ROLE_PRODUCT_ADMIN";
            } 
            else if (user.isOrdersAdmin()) {
                role = "ROLE_ORDERS_ADMIN";
            } else {
                role = "ROLE_USER";
            }

            String jwt = jwtUtil.generateToken(email, role, user.getUserId());
            return new LoginResponseDTO(jwt,user, isNewUser ? "New user registered via Google & make your profile complete by providing mobile number and address" : "Login successful via Google");

        } catch (Exception e) {
            throw new RuntimeException("Token verification failed: " + e.getMessage());
        }
    }
}



