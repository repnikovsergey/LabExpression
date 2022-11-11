package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String express = sc.next();
        Expression ex1 = new Expression(express);
        System.out.print("Результат: " + ex1.result() + "\n");
        sc.close();
    }
}