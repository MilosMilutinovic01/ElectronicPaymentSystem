package org.bankexample.bankbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(value = "/payment")
public class PaymentViewController {

    @GetMapping("/{paymentId}")
    public String paymentForm(@PathVariable UUID paymentId, Model model) {
        model.addAttribute("paymentId", paymentId);
        return "card-form";  // Resolves to src/main/resources/templates/card-form.html
    }
}
