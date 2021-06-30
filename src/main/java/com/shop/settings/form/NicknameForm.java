package com.shop.settings.form;

import com.shop.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class NicknameForm {

    @NotBlank
    @Length(min = 1, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{1,20}$")
    private String nickname;


    public NicknameForm(Account account) {
        this.nickname = account.getNickname();
    }
}
