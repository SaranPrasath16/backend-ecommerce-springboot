package com.ecommerce.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Orders;

@Repository
public interface OrderRepo extends MongoRepository<Orders, String> {
	
    @Query("{'userId' : ?0}")
    List<Orders> findByUserId(String userId);
    
    @Query(value = "{'_id' : ?0}", delete = true)
    long deleteByOrderId(String orderId);
    
    @Query("{'orderStatus': { $regex: ?0, $options: 'i' }}")
    List<Orders> findByStatus(String status);
    
    @Query("{ 'paymentId' : ?0 }")
	Orders findByPaymentId(String paymentId);

    @Query(value = "{ 'userId': ?0, 'orderStatus': ?2, 'cartItems.productId': ?1 }", exists = true)
    Boolean hasUserPurchasedProduct(String userId, String productId, String status);

}
