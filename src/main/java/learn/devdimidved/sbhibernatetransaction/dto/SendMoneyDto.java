package learn.devdimidved.sbhibernatetransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMoneyDto {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}
