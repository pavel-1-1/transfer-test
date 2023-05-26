package com.example.springdemo.userCard;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardTransfer {
    @NotEmpty
    @Pattern(regexp = "\\d{16}", message = "No 16 size")
    private String cardFromNumber;
    @NotEmpty
    @Pattern(regexp = "(\\d|1[0-2])/\\d{2}")
    private String cardFromValidTill;
    @NotEmpty
    @Pattern(regexp = "\\d{3}")
    private String cardFromCVV;
    @NotEmpty
    @Pattern(regexp = "\\d{16}")
    private String cardToNumber;

    private Amount amount;
}
