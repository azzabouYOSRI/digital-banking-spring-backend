package org.sid.ebankingbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.sid.ebankingbackend.entities.BankAccount;

import java.util.List;

@Data
@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
