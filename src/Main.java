
import java.util.Scanner;
  // класс для преобразования из римской системы
 class RomanToInt {
     int value(char r) {
         if (r == 'I')
             return 1;
         if (r == 'V')
             return 5;
         if (r == 'X')
             return 10;
         if (r == 'L')
             return 50;
         if (r == 'C')
             return 100;
         if (r == 'D')
             return 500;
         if (r == 'M')
             return 1000;
         return -1;
     }

     //function to convert roman to integer
     int convertRomanToInt(String s) {
//variable to store the sum
         int total = 0;
//loop iterate over the string (given roman numeral)
//getting value from symbol s1[i]
         for (int i = 0; i < s.length(); i++) {
             int s1 = value(s.charAt(i));
//getting value of symbol s2[i+1]
             if (i + 1 < s.length()) {
                 int s2 = value(s.charAt(i + 1));
//comparing the current character from its right character
                 if (s1 >= s2) {
//if the value of current character is greater or equal to the next symbol
                     total = total + s1;
                 } else {
//if the value of the current character is less than the next symbol
                     total = total - s1;
                 }
             } else {
                 total = total + s1;
             }
         }
//returns corresponding integer value
         return total;
     }
 }
public class Main {
        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);
            // take input from the user
            System.out.println("Введите выражение без пробелов");
            String inputString = input.nextLine();
            System.out.println("Результат:");
            String outputString = calc(inputString);
            System.out.println(outputString);
        }

    /**
     * Основной метод решения задачи
     * @param input- входящая строка
     * @return - возвращает строку - результат работы программы
     */
    public static String calc(String input) {
       // разбивание строки по знаку + Проверка что знак один
        int signIndex;
        try {
            signIndex=IndexOfSign(input);
        }
        catch ( Exception exception) {
            System.out.println("неверное выражение Количество знаков должно быть равно 1");
            return null;
        }

        String string1 = input.substring(0, signIndex);
        String string2 = input.substring(signIndex + 1);






        try {
            Integer.parseInt(string1);
        } catch (NumberFormatException exception) {
            try {
                Integer.parseInt(string2);
                System.out.println("неверный формат ввода одновременно только целые целые арабские или римские цифры");
                return null;

            }
            catch (NumberFormatException e)
            {
                return RomanNumbersOperation(string1,string2,input.substring(signIndex,signIndex+1));
            }

        }

        try {
            Integer.parseInt(string2);
            return  ArabicNumbersOperation( Integer.parseInt(string1), Integer.parseInt(string2), input.substring(signIndex,signIndex+1));
        }
        catch (NumberFormatException exception)
        {
            System.out.println("неверный формат ввода одновременно только целые арабские  или римские цифры");
            return null;
        }

    }
    /**
     * Метод находит положения знака в строке
     * @param  input - входящая строка
     * @return - возвращает индекс знака или исключение если их больше 1
     */
    static int IndexOfSign(String input) throws Exception {
        int signsCount = 0;

        char[] signs = new char[]{'/', '-', '+', '*'};
        int signIndex = 0;

            for (int i = 0; i < input.length(); i++) {
                for (int j = 0; j < 4; j++) {
                    if (input.charAt(i) == signs[j]) {
                        signIndex = i;
                        signsCount++;
                    }
                }
            }
            if (signsCount != 1)
                throw new Exception();
        return signIndex;
    }
    /**
     * Метод возвращает итог опреации в виде строки
     * из трех переданных аргументов
     * @param number1 - первое число
     * @param number2 - второе число
     * @param sign - знак операции
     * @return - возвращает строку - итог операции
     */
    static String ArabicNumbersOperation(int number1, int number2,String sign) {

        switch (sign) {
            case ("/"):
                return Integer.toString(number1 / number2);
            case ("*"):
                return Integer.toString(number1 * number2);
            case ("-"):
                return Integer.toString(number1 - number2);
            case ("+"):
                return Integer.toString(number1 +number2);

        }
        return null;
    }
    /**
     * Метод возвращает итог опреации в виде строки для римских цифр
     * из трех переданных аргументов
     * @param romanNumber1 - первое римское число
     * @param romanNumber2 - второе римское число
     * @param sign - знак операции
     * @return - возвращает строку - римское число, итог операции
     */
    static String RomanNumbersOperation(String romanNumber1, String romanNumber2,String sign)
    {
        RomanToInt roman= new RomanToInt();
        int number1 = roman.convertRomanToInt(romanNumber1);
        int number2 = roman.convertRomanToInt(romanNumber2);
        String resultArab= ArabicNumbersOperation(number1,number2,sign);

        try {
            if (Integer.parseInt(resultArab) < 1) {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("разница римских цифр не может быть отрицательна");
            return null;
        }
        return (intToRoman(Integer.parseInt(resultArab)));

    }
    /**
     * Метод преобразует integer в римское число( строку)
     * из трех переданных аргументов
     * @param num - int
     * @return - возвращает строку - римское число, итог операции
     */
    static String intToRoman(int num)
    {

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++)
        {
            while(num >= values[i])
            {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }



}