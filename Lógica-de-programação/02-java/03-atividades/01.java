package aula03;

import java.util.Scanner;

public class Ex01 {
    public static void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite a temperatura celsius: ");
        double cel = input.nextDouble();
        double fah = (cel * 1.8) +32;
        System.out.print("A temperatura em fahrenheit é: " + fah);
    }
}
