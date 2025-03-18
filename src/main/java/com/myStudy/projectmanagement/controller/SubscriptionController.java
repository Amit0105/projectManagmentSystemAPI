package com.myStudy.projectmanagement.controller;

import com.myStudy.projectmanagement.modal.PlanType;
import com.myStudy.projectmanagement.modal.Subscription;
import com.myStudy.projectmanagement.modal.User;
import com.myStudy.projectmanagement.service.SubscriptionService;
import com.myStudy.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.getUsersSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);

    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType planType) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);

        return new ResponseEntity<>(subscription, HttpStatus.OK);

    }

}
