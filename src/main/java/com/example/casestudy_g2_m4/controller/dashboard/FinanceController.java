package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.Payment;
import com.example.casestudy_g2_m4.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class FinanceController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("finance")
    public String showFinance(Model model){
        List<Payment> payments = paymentService.findAll();
        model.addAttribute("payments",payments);
        return "dashboard/finance/list_finance";
    }
}
