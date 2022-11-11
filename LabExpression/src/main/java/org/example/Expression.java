package org.example;

import java.util.Stack;

/**
 * Класс Expression проверяет математические выражения на правильность
 * и считает их результат
 * @author Сергей Репников
 */
public class Expression {
    String express;

    /**
     * Конструктор класса
     * @param str выражение
     */
    public Expression(String str) {
        express = str;
    }

    /**
     * Метод проверки выражения на правильность
     * @return True - если выражение написано правильно
     * False - если выражение написано неправильно
     */
    public boolean ExpressionIsCorrect() {
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < this.express.length(); i++) {
            char elem = this.express.charAt(i);
            if (elem == '(')
                st.push(elem);
            else {
                if (elem == ')' && (st.isEmpty() || st.pop() != '('))
                    return false;
            }
        }
        return st.isEmpty();
    }

    /**
     * Метод, преобразующий инфиксное выражение в постфиксное
     * @return Постфиксное выражение
     */
    public String PostForm() {
        int num = 0;
        String res = "";
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < this.express.length(); i++) {
            char elem = this.express.charAt(i);
            if (elem == '+' || elem == '-' || elem == '*' || elem == '/') {
                while (!st.isEmpty() && Ratio(elem) <= Ratio(st.peek())) {
                    res += st.pop();
                }
                st.push(elem);
                num = 0;
            } else if (elem == '(') {
                st.push(elem);
                num = 0;
            } else if (elem == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    res += st.pop();
                }
                st.pop();
                num = 0;
            } else {
                if (num == 0) {
                    res += ' ';
                    res += elem;
                    res += ' ';
                } else {
                    res=LastChar(res);
                    res += elem;
                    res += ' ';
                }
                num += 1;
            }
        }
        while (!st.isEmpty()) {
            res += st.pop();
        }
        return res;
    }
    /**
     * Метод, определяющий коэффициент (приоритет) операции.
     * Используется в методе преобразования инфиксного выражения в постфиксное
     * @param ch Операция
     * @return Приоритет операции
     */
    public int Ratio(char ch) {
        int res = 0;
        if (ch == '+' || ch == '-') {
            res = 1;
        } else if (ch == '*' || ch == '/') {
            res = 2;
        }
        return res;
    }

    /**
     * Метод, который позволяет удалить последний символ строки.
     * Используется в методе преобразования инфиксного выражения в постфиксное
     * @param str Строка
     * @return Строка без последнего символа
     */
    public static String LastChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * Метод вычисляющий значение выражения
     * @return Значение выражения или ошибку
     */
    public double result() {
        if (this.ExpressionIsCorrect()) {
            Stack<Double>st=new Stack<Double>();
            String post=this.PostForm();
            String num="";
            for (int i = 0; i < post.length(); i++) {
                char elem = post.charAt(i);
                if (elem == '+') {
                    double value2 = st.pop();
                    double value1 = st.pop();
                    st.push(value1 + value2);
                } else if (elem == '-') {
                    double value2 = st.pop();
                    double value1 = st.pop();
                    st.push(value1 - value2);
                } else if (elem == '*') {
                    double value2 = st.pop();
                    double value1 = st.pop();
                    st.push(value1 * value2);
                } else if (elem == '/') {
                    double value2 = st.pop();
                    double value1 = st.pop();
                    st.push(value1 / value2);
                }
                else{
                    i+=1;
                    elem=post.charAt(i);
                    while (elem != ' ') {
                        num += elem;
                        i += 1;
                        elem = post.charAt(i);
                    }
                    double value = Double.parseDouble(num);
                    st.push(value);
                    num = "";
                }
            }
            return st.pop();
        }
        else
        {
            System.out.println("Выражение введено неверно.");
            return 0;
        }
    }
}


