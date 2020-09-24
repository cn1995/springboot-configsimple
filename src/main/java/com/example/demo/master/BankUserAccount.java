package com.example.demo.master;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerationStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class BankUserAccount {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    String uuid;
    String userId;
    String userName;
    String userAccount;
    String userPassword;
    BigDecimal money;

}
