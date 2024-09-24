package com.example.PaymentService.repository;
import com.example.PaymentService.entity.TransactionDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long>
{

    TransactionDetails findByOrderId(long orderId);

}
