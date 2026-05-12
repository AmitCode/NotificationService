package com.notifications.template;

public class RegistrationEmailTemplate {
    public static String generateRegistrationEmailTemplate(String userName, String userEmail, String verifyLinkUrl){
        return String.format("Click below link to verify your email address : %s and user name: %s to complete setup for your user account" +
                "<a href=\"%s\">Click Here!.</a>",
                userName, userEmail, verifyLinkUrl);
    }
}
