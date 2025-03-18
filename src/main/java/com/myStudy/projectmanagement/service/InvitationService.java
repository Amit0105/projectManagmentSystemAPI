package com.myStudy.projectmanagement.service;

import com.myStudy.projectmanagement.modal.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    public void sendInvitation (String email,Long projectId) throws MessagingException;

    public Invitation acceptInvitation(String token,Long projectId) throws Exception;

    public String getTokenByUserEmail(String userEmail);

    void deleteToken(String token);
}
