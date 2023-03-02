package com.example.presentationExample1;

import java.util.ArrayList;
import java.util.List;

//Is there any problem with this code?

public class Main {

    public static void main(String args[]) {
        List myList = new ArrayList();

        myList.add("one");

        for (int i = 0; i < myList.size(); i++) {
            String str = myList.get(i);
            System.out.println("The " + i + "th element is: " + str);
        }
    }
}