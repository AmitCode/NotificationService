package com.notifications.template;

public class RegistrationEmailTemplate {
    public static String generateRegistrationEmailTemplate(String userName, String userEmail, String verifyLinkUrl){
        return String.format("Click below link to verify and complete your user account registration." +
                "<a href=\"%s\">Click Here!.</a> ", verifyLinkUrl);
    }
}
