package com.shop.account;

import com.shop.account.form.SignUpForm;
import com.shop.domain.Account;
import com.shop.domain.OrdersProduct;
import com.shop.ordersProduct.OrdersProductRepository;
import com.shop.ordersProduct.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final OrdersProductRepository ordersProductRepository;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("sign-up")
    public String signUpSubmit(@Valid @ModelAttribute SignUpForm signUpForm, Errors errors) {
        if(errors.hasErrors()) {
            return "account/sign-up";
        }
//        signUpFormValidator.validate(signUpForm, errors);
        Account account = accountService.saveNewAccount(signUpForm);
        accountService.completeSignUp(account);
        return "redirect:/";
    }

    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, Model model, @CurrentUser Account account) {
        Account byNickname = accountRepository.findByNickname(nickname);
        List<OrdersProduct> product = ordersProductRepository.findByAccountAndOrdersIsNull(account);
        List<OrdersProduct> orders = ordersProductRepository.findByAccountAndOrdersIsNotNull(account);
        if(nickname == null) {
            throw new IllegalArgumentException(nickname + "에 해당하는 사용자가 없습니다");
        }

        model.addAttribute("account", byNickname);
        model.addAttribute("product", product);
        model.addAttribute("orders", orders);
        model.addAttribute("isOwner", byNickname.equals(account));
        return "account/profile";
    }



}
