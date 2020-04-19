package de.studiocode.antibackdoor.userinput;

import java.util.Scanner;

public class YesNoQuestion {
    
    private String question;

    public YesNoQuestion(String question) {
        this.question = question;
    }
    
    public boolean askQuestion() {
        System.out.println(question + " [y/n]");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        sc.close();
        
        return in.equalsIgnoreCase("y") || in.equalsIgnoreCase("yes");
    }
    
}
