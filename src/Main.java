import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите пример: ");
        try {
            System.out.println(calc(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String calc (String input) throws Exception {
        List<String> romans = Arrays.asList(
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        );

        String[] vars = input.split(" ");

        if (vars.length != 3){
            throw new Exception("Пример введен некоректно. Ожидаемая форма записи: a + b");
        }

        if (vars[1].length() > 1){
            throw new Exception("Операция введена неверно. Ожидаются +, -, *, /.");
        }

        String number1 = vars[0];
        char operation = vars[1].charAt(0);
        String number2 = vars[2];

        int operand1;
        int operand2;

        boolean isRoman = false;

        if (romans.contains(number1) && romans.contains(number2)) {
            isRoman = true;
            operand1 = romans.indexOf(number1) + 1;
            operand2 = romans.indexOf(number2) + 1;
        } else {
            try {
                operand1 = Integer.parseInt(number1);
                operand2 = Integer.parseInt(number2);
            } catch (Exception e){
                throw new Exception("Калькулятор умеет работать только с арабскими или римскими " +
                        "целыми числами одновременно.");
            }
        }

        if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10){
            throw new Exception("Числа должны быть в диапазоне от 1 до 10 включительно.");
        }

        int result = switch (operation) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            default -> throw new Exception("Некоректная операция. Ожидаются +, -, *, /.");
        };

        if (isRoman){
            if (result < 1){
                throw new Exception("Результатом работы калькулятора с римскими числами могут быть " +
                        "только положительные числа.");
            } else {
                return romans.get(result - 1);
            }
        }

        return Integer.toString(result);
    }
}