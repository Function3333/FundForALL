package com.fundingForAll.www.donate;

import com.fundingForAll.www.fund.Fund;
import com.fundingForAll.www.user.User;
import lombok.Data;

@Data
public class DonateForm {
    private String useId;
    private int fundId;
    private User user;
    private Fund fund;
    private int donateMoney;
}
