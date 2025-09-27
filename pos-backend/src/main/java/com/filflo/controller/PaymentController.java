package com.filflo.controller;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.filflo.domain.PaymentMethod;
import com.filflo.exception.UserException;
import com.filflo.modal.PaymentOrder;
import com.filflo.modal.User;
import com.filflo.payload.response.PaymentLinkResponse;
import com.filflo.service.PaymentService;
import com.filflo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Long planId,
            @RequestParam PaymentMethod paymentMethod) throws UserException, RazorpayException, StripeException {


            User user = userService.getUserFromJwtToken(jwt);



            PaymentLinkResponse paymentLinkResponse =
                    paymentService.createOrder(user, planId, paymentMethod);
            return ResponseEntity.ok(paymentLinkResponse);


    }



    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId) throws Exception {

            PaymentOrder paymentOrder = paymentService.
                    getPaymentOrderByPaymentId(paymentLinkId);
            Boolean success = paymentService.ProceedPaymentOrder(
                    paymentOrder,
                    paymentId, paymentLinkId);
            return ResponseEntity.ok(success);

    }


}
