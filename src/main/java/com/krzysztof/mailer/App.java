package com.krzysztof.mailer;


public class App {

    public static void main( String[] args ) {

        MailerService mailerService = new MailerService("user@email.com", "password");

        boolean operationStatus =  mailerService.sendEmail("sender@email.com", "consumer@email.com",
                "Knock, knock it' JAVA ;-)");

        if (operationStatus) {
            System.out.println("Email sent!");
        } else {
            System.out.println("Upss.. Something wrong!");
        }
    }
}
