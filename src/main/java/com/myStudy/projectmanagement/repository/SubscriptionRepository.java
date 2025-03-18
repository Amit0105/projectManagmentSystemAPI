package com.myStudy.projectmanagement.repository;

import com.myStudy.projectmanagement.modal.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long userId);

}
