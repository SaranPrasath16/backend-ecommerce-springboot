package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.dto.LoginResponseDTO;
import com.ecommerce.dto.OtpRequestDTO;
import com.ecommerce.dto.ProductGetResponseDTO;
import com.ecommerce.dto.ProfileCompletionDTO;
import com.ecommerce.model.User;
import com.ecommerce.services.user.UserImpl;
import com.ecommerce.services.user.UserRegisterLoginService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class HomeController {

	private final UserRegisterLoginService userRegisterLoginService;
	private final UserImpl userImpl;
	
    public HomeController(UserRegisterLoginService userRegisterLoginService, UserImpl userImpl) {
		super();
		this.userRegisterLoginService = userRegisterLoginService;
		this.userImpl = userImpl;
	}
    
	@GetMapping("/home")
    public ResponseEntity<String> getHome(){
        return  ResponseEntity.ok("Welcome to the QuickPikk!");
    }
	
	@GetMapping("/homepage")
    public ResponseEntity<List<ProductGetResponseDTO>> getHomePage(){
        List<ProductGetResponseDTO> productList = userImpl.getHomePage();
        return  ResponseEntity.ok(productList);
    }
	
    @GetMapping("/randomproducts")
    public ResponseEntity<List<ProductGetResponseDTO>> getRandomProducts() {
        List<ProductGetResponseDTO> randomProducts = userImpl.getRandomProducts();
        return ResponseEntity.ok(randomProducts);
    }

	@PostMapping("/user/register")
	public ResponseEntity<String> registerUser(@RequestBody User userRequest){
	    String msg = userRegisterLoginService.registerUser(userRequest);
	    return ResponseEntity.ok(msg);
	}
	
    @PostMapping("/user/otp")
    public ResponseEntity<String> verifyUserOtp(@RequestBody OtpRequestDTO otpRequest){
        String msg = userRegisterLoginService.verifyUserOtp(otpRequest);
        return ResponseEntity.ok(msg);
    }
    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response){
        LoginResponseDTO loginResponseDTO = userRegisterLoginService.validateUser(loginRequestDTO);
        User user=loginResponseDTO.getUser();
        String role="USER";
        response.setHeader("Authorization", "Bearer " + loginResponseDTO.getToken());
        response.setHeader("userName", user.getUserName());
        response.setHeader("userEmail", user.getEmail());
        response.setHeader("userAddress", user.getAddress());
        response.setHeader("userMobile", String.valueOf(user.getMobile()));
        response.setHeader("userId", user.getUserId());
        if(user.isMainAdmin()) {
        	role="SUPER_ADMIN";
        }
        else if(user.isProductAdmin()) {
        	role="PRODUCT_ADMIN";
        }
        else if(user.isOrdersAdmin()) {
        	role="ORDER_ADMIN";
        }
        response.setHeader("role", role);
        return ResponseEntity.ok(loginResponseDTO.getMessage());
    }
   
    @PutMapping("/user/complete-profile")
    public ResponseEntity<String> completeUserProfile(@RequestBody ProfileCompletionDTO profileDTO, @RequestHeader("Authorization") String token) {
        String message = userRegisterLoginService.completeProfile(profileDTO, token);
        return ResponseEntity.ok(message);
    }
}


