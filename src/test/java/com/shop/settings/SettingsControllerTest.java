package com.shop.settings;

import com.shop.WithAccount;
import com.shop.account.AccountRepository;
import com.shop.account.AccountService;
import com.shop.domain.Account;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static com.shop.settings.SettingsController.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("프로필 화면")
    @Test
    void updateProfileForm() throws Exception {
        String bio = "짧게 소개를 수정하는 경우";
        mockMvc.perform(get(ROOT + SETTING + PROFILE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"));
    }


    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("프로필 수정하기 - 입력값 정상")
    @Test
    void updateProfile() throws Exception {
        String bio = "짧게 소개를 수정하는 경우";
        mockMvc.perform(post(ROOT + SETTING + PROFILE)
                .with(csrf())
                .param("bio", bio))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(ROOT + SETTING + PROFILE))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByEmail("tkddnjs5652@naver.com");
        Assertions.assertEquals(bio, account.getBio());
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("프로필 수정하기 - 입력값 에러")
    @Test
    void updateProfile_errors() throws Exception {
        String bio = "길게 소개를 수정하는 경우 / 길게 소개를 수정하는 경우 / 길게 소개를 수정하는 경우 / 길게 소개를 수정하는 경우 / 길게 소개를 수정하는 경우";
        mockMvc.perform(post(ROOT + SETTING + PROFILE)
                .with(csrf())
                .param("bio", bio))
                .andExpect(status().isOk())
                .andExpect(view().name(SETTING + PROFILE))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"))
                .andExpect(model().hasErrors());

        Account account = accountRepository.findByEmail("tkddnjs5652@naver.com");
        Assertions.assertNull(account.getBio());
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("패스워드 화면")
    @Test
    void updatePasswordForm() throws Exception {
        mockMvc.perform(get(ROOT + SETTING + PASSWORD)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("passwordForm"));
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("패스워드 수정 - 입력 정상")
    @Test
    void updatePassword() throws Exception {
        mockMvc.perform(post(ROOT + SETTING + PASSWORD)
                .param("newPassword", "12345678")
                .param("newPasswordConfirm", "12345678")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(ROOT + SETTING + PASSWORD))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByEmail("tkddnjs5652@naver.com");
        Assertions.assertTrue(passwordEncoder.matches("12345678", account.getPassword()));

    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("패스워드 수정 - 입력 실패")
    @Test
    void updatePassword_error() throws Exception {
        mockMvc.perform(post(ROOT + SETTING + PASSWORD)
                .param("newPassword", "1234567")
                .param("newPasswordConfirm", "1111111")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SETTING + PASSWORD))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("passwordForm"));
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("닉네임 수정 화면")
    @Test
    void updateNicknameForm() throws Exception {
        mockMvc.perform(get(ROOT + SETTING + ACCOUNT)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("nicknameForm"));
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("닉네임 수정 - 입력 정상")
    @Test
    void updateNickname() throws Exception {
        Account byNickname = accountRepository.findByNickname("test");
        Assertions.assertNull(byNickname);

        mockMvc.perform(post(ROOT + SETTING + ACCOUNT)
                .param("nickname", "test")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(ROOT + SETTING + ACCOUNT))
                .andExpect(flash().attributeExists("message"));
    }

    @WithAccount("tkddnjs5652@naver.com")
    @DisplayName("닉네임 수정 - 입력 실패")
    @Test
    void updateNickname_error() throws Exception {
        mockMvc.perform(post(ROOT + SETTING + ACCOUNT)
                .param("nickname", "test*")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SETTING + ACCOUNT))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("nicknameForm"));
    }

}