package com.example.upbit.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private String currency;
    private double balance;
    private double locked;
    private double avg_buy_price;
    private boolean avg_buy_price_modified;
    private String unit_currency;

}
