package com.fundingForAll.www.Donate;

import com.fundingForAll.www.Fund.Fund;
import com.fundingForAll.www.User.User;
import lombok.Data;

@Data
public class DonateForm {
    private String useId;
    private int fundId;
    private User user;
    private Fund fund;
    private int donateMoney;
}
