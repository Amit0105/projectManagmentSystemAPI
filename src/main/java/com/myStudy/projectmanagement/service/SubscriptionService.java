package com.myStudy.projectmanagement.service;

import com.myStudy.projectmanagement.modal.PlanType;
import com.myStudy.projectmanagement.modal.Subscription;
import com.myStudy.projectmanagement.modal.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subscription);
}
