package com.shop.ordersProduct;

import com.shop.account.AccountRepository;
import com.shop.account.CurrentUser;
import com.shop.domain.Account;
import com.shop.domain.OrdersProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrdersProductController {

    private final OrdersProductRepository ordersProductRepository;
    private final OrdersRepository ordersRepository;

    @PostMapping("/ordersProduct/{nickname}/delete/{id}")
    public String deletePersonalProduct(@PathVariable OrdersProduct id,
                                        @PathVariable String nickname) {
        ordersProductRepository.delete(id);
        //ordersRepository.delete(id.getOrders());

        return "redirect:/profile/{nickname}";
    }


}
