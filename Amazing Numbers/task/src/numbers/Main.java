package numbers;

import java.util.*;

enum Parameters {
    EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        showInstructions();
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("Enter a request: > ");
        while (scanner.hasNextLine()) {
            input = scanner.nextLine().trim();
            if (input.equals("0")) {
                scanner.close();
                System.out.println();
                System.out.println("Goodbye!");
                break;
            }
            String[] list = input.split(" ");
            if (list.length > 4) {
                list = removeDuplicatesFromPosition(list);
                input = Arrays.toString(list);
            }

            if (list.length == 2) {
                long number = Long.parseLong(list[0]);
                int increment = Integer.parseInt(list[1]);
                if (number > 0 && increment > 0) {
                    for (int i = 0; i < increment; i++) {
                        System.out.println(checkForAll(number + i));
                    }
                } else if (increment <= 0) {
                    System.out.println("The second parameter should be a natural number.");
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            } else if (list.length > 2) {
                if (isMistakenWordOrWords(list)) {
                    if (isExclusiveProperty(list, input)) {
                        checkPropertyForAll(list);
                    } else {
                        System.out.format("""
                                The request contains mutually exclusive properties: [%s]
                                There are no numbers with these properties.""", isExclusivePropertyString(list, input));
                    }
                } else {
                    System.out.format("""
                            %s
                            Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN,
                             ODD, HAPPY, SAD]
                            """, printMistakenProperty(list));
                }
            } else {
                if (input.isBlank()) {
                    showInstructions();
                } else if (!list[0].matches("\\D+")) {
                    long number = Long.parseLong(list[0]);
                    System.out.println();
                    if (number > 0) {
                        System.out.println("Properties of " + number);
                        printProperties(number);
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                } else {
                    System.out.println();
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            System.out.println();
            System.out.print("Enter a request: > ");
        }
    }

    public static void showInstructions() {
        String hello = """
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
        System.out.println(hello);
    }

    public static boolean isDuck(long number) {
        boolean duck = false;
        while (number != 0) {
            if (number % 10 == 0) {
                duck = true;
                break;
            }
            number = number / 10;
        }
        return duck;
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return number % 2 != 0;
    }

    public static boolean isBuzz(long number) {
        return (number % 10 == 7 || number % 7 == 0);
    }

    public static boolean isPalindromic(long number) {
        String string = String.valueOf(number);
        int length = string.length();
        int halfOfString = length / 2;
        String firstHalf = string.substring(0, halfOfString);
        StringBuilder secondHalf;
        if (length % 2 == 0) {
            secondHalf = new StringBuilder(string.substring(halfOfString));
        } else {
            secondHalf = new StringBuilder(string.substring(halfOfString + 1));
        }
        secondHalf.reverse();
        return firstHalf.contentEquals(secondHalf);
    }

    public static boolean isGapful(long number) {
        String string = String.valueOf(number);
        String firstLetter = String.valueOf(string.charAt(0));
        String endLetter = String.valueOf(string.charAt(string.length() - 1));
        String stringNum = firstLetter + endLetter;
        int digit = Integer.parseInt(stringNum);
        if (string.length() > 2) {
            return number % digit == 0;
        } else {
            return false;
        }
    }

    public static String checkForAll(long number) {
        StringBuilder line = new StringBuilder(number + " is ");
        List<String> nameOfMethod = new ArrayList<>();
        if (isDuck(number)) {
            nameOfMethod.add("duck");
        }
        if (isEven(number)) {
            nameOfMethod.add("even");
        }
        if (isOdd(number)) {
            nameOfMethod.add("odd");
        }
        if (isBuzz(number)) {
            nameOfMethod.add("buzz");
        }
        if (isSpy(number)) {
            nameOfMethod.add("spy");
        }
        if (isGapful(number)) {
            nameOfMethod.add("gapful");
        }
        if (isPalindromic(number)) {
            nameOfMethod.add("palindromic");
        }
        if (isSunny(number)) {
            nameOfMethod.add("sunny");
        }
        if (isJumping(number)) {
            nameOfMethod.add("jumping");
        }
        if (isSquare(number)) {
            nameOfMethod.add("square");
        }
        if (isHappy(number)) {
            nameOfMethod.add("happy");
        }
        if (isSad(number)) {
            nameOfMethod.add("sad");
        }
        for (int i = 0; i < nameOfMethod.size(); i++) {
            if (i == nameOfMethod.size() - 1) {
                line.append(nameOfMethod.get(i));
            } else {
                line.append(nameOfMethod.get(i)).append(", ");
            }
        }
        return line.toString();
    }

    public static void printProperties(long number) {
        System.out.println("buzz: " + isBuzz(number));
        System.out.println("duck: " + isDuck(number));
        System.out.println("palindromic: " + isPalindromic(number));
        System.out.println("gapful: " + isGapful(number));
        System.out.println("spy: " + isSpy(number));
        System.out.println("even: " + isEven(number));
        System.out.println(" odd: " + isOdd(number));
        System.out.println("sunny: " + isSunny(number));
        System.out.println("square: " + isSquare(number));
        System.out.println("jumping: " + isJumping(number));
        System.out.println("happy: " + isHappy(number));
        System.out.println("sad: " + isSad(number));
    }

    public static boolean isSpy(long number) {
        String[] stringNumber = String.valueOf(number).split("");
        int sumOfDigits = 0;
        int productOfDigits = Integer.parseInt(stringNumber[0]);
        for (String s : stringNumber) {
            sumOfDigits += Integer.parseInt(s);
        }
        for (int i = 1; i < stringNumber.length; i++) {
            productOfDigits *= Integer.parseInt(stringNumber[i]);
        }
        return sumOfDigits == productOfDigits;
    }

    public static boolean isProperty(String nameOfProperty, long number) {
        nameOfProperty = nameOfProperty.toLowerCase();
        switch (nameOfProperty) {
            case "buzz" -> {
                return isBuzz(number);
            }
            case "duck" -> {
                return isDuck(number);
            }
            case "palindromic" -> {
                return isPalindromic(number);
            }
            case "gapful" -> {
                return isGapful(number);
            }
            case "spy" -> {
                return isSpy(number);
            }
            case "even" -> {
                return isEven(number);
            }
            case "odd" -> {
                return isOdd(number);
            }
            case "sunny" -> {
                return isSunny(number);
            }
            case "square" -> {
                return isSquare(number);
            }
            case "jumping" -> {
                return isJumping(number);
            }
            case "happy" -> {
                return isHappy(number);
            }
            case "sad" -> {
                return isSad(number);
            }
        }
        return false;
    }

    public static boolean isSunny(long number) {
        number += 1;
        double numOfSquard = Math.sqrt(number);
        return number % numOfSquard == 0;
    }

    public static boolean isSquare(long number) {
        double numOfSquard = Math.sqrt(number);
        return number % numOfSquard == 0;
    }

    public static boolean isExclusiveProperty(String[] listOfProperties, String line) {
        int first = 0, second = 0, third = 0, forth = 0, fifth = 0, sixth = 0, seventh = 0,
                eight = 0, ninth = 0, tenth = 0, eleventh = 0, twelve = 0, thirteen = 0, fourteen = 0,
                fifthteen = 0, sixteen = 0, seventeen = 0, eightteen = 0, ninthteen = 0;

        for (int i = 2; i < listOfProperties.length; i++) {
            if ((listOfProperties[i].equalsIgnoreCase("even") ||
                    listOfProperties[i].equalsIgnoreCase("odd"))) {
                first++;
            } else if (listOfProperties[i].equalsIgnoreCase("duck")
                    && line.contains("spy")) {
                second++;
            } else if ((listOfProperties[i].equalsIgnoreCase("-duck")
                    || listOfProperties[i].equalsIgnoreCase("duck"))) {
                third++;
            } else if ((listOfProperties[i].equalsIgnoreCase("-spy")
                    || listOfProperties[i].equalsIgnoreCase("spy"))) {
                forth++;
            } else if ((listOfProperties[i].equalsIgnoreCase("sunny")
                    && line.contains("square"))) {
                fifth++;
            } else if ((listOfProperties[i].equalsIgnoreCase("-sunny")
                    || listOfProperties[i].equalsIgnoreCase("sunny"))) {
                sixth++;
            } else if ((listOfProperties[i].equalsIgnoreCase("square")
                    || listOfProperties[i].equalsIgnoreCase("-square"))) {
                seventh++;
            } else if ((listOfProperties[i].equalsIgnoreCase("sad")
                    && line.contains("happy"))) {
                eight++;
            } else if ((listOfProperties[i].equalsIgnoreCase("-sad")
                    || listOfProperties[i].equalsIgnoreCase("sad"))) {
                ninth++;
            } else if ((listOfProperties[i].equalsIgnoreCase("happy")
                    || listOfProperties[i].equalsIgnoreCase("-happy"))) {
                tenth++;
            } else if ((listOfProperties[i].equalsIgnoreCase("palindromic")
                    || listOfProperties[i].equalsIgnoreCase("-palindromic"))) {
                eleventh++;
            } else if ((listOfProperties[i].equalsIgnoreCase("gapful")
                    || listOfProperties[i].equalsIgnoreCase("-gapful"))) {
                twelve++;
            } else if ((listOfProperties[i].equalsIgnoreCase("jumping")
                    || listOfProperties[i].equalsIgnoreCase("-jumping"))) {
                thirteen++;
            } else if ((listOfProperties[i].equalsIgnoreCase("buzz")
                    || listOfProperties[i].equalsIgnoreCase("-buzz"))) {
                fourteen++;
            }
            if (listOfProperties[i].equalsIgnoreCase("even") ||
                    listOfProperties[i].equalsIgnoreCase("-odd")) {
                fifthteen++;
            }
            if (listOfProperties[i].equalsIgnoreCase("-even") ||
                    listOfProperties[i].equalsIgnoreCase("odd")) {
                sixteen++;
            }
            if (listOfProperties[i].equalsIgnoreCase("odd") ||
                    listOfProperties[i].equalsIgnoreCase("-odd")) {
                seventeen++;
            }
            if (listOfProperties[i].equalsIgnoreCase("even") ||
                    listOfProperties[i].equalsIgnoreCase("-even")) {
                eightteen++;
            }
            if (listOfProperties[i].equalsIgnoreCase("-even") ||
                    listOfProperties[i].equalsIgnoreCase("-odd")) {
                ninthteen++;
            }
        }
        return first < 2 && second < 1 && third < 2 && forth < 2 && fifth < 1 && sixth < 2 && seventh < 2 && eight < 1 && ninth < 2 && tenth < 2 &&
                eleventh < 2 && twelve < 2 && thirteen < 2 && fourteen < 2 && fifthteen <= 2
                && sixteen <= 2 && seventeen < 2 && eightteen < 2 && ninthteen < 2;


    }

    public static boolean isJumping(long number) {
        String numberAsString = String.valueOf(number);
        String[] arrayOfDigits = numberAsString.split("");
        int[] digits = Arrays.stream(arrayOfDigits).mapToInt(Integer::parseInt).toArray();
        int firstDigit = digits[0];
        int count = 1;
        for (int i = 1; i < digits.length; i++) {
            if (firstDigit == digits[i] - 1 || firstDigit == digits[i] + 1) {
                count++;
                firstDigit = digits[i];
            } else {
                break;
            }
        }
        return count == digits.length;

    }

    public static void checkPropertyForAll(String[] array) {
        long firstDigit = Integer.parseInt(array[0]);
        long countOfDigits = Integer.parseInt(array[1]);
        long count = 0;

        while (count != countOfDigits) {
            if (checkAllProperties1(array, firstDigit)) {
                System.out.println(checkForAll(firstDigit));
                count++;
            }
            firstDigit++;
        }
    }

    public static String printMistakenProperty(String[] array) {
        StringBuilder lineAnswer = new StringBuilder();
        int count = 0;
        for (int i = 2; i < array.length; i++) {
            String property = array[i];
            if (array[i].startsWith("-")) {
                property = property.replace("-", "").trim();
            }
            if (!Arrays.toString(Parameters.values()).contains(property.toUpperCase())) {
                count++;
                if (count > 1) {
                    lineAnswer.append(", ").append(array[i]);
                } else {
                    lineAnswer.append(array[i]);
                }
            }
        }
        String line;
        if (count > 1) {
            line = String.format("The properties [%s] are wrong.", lineAnswer.toString().toUpperCase());
        } else {
            line = String.format("The property [%s] is wrong.", lineAnswer.toString().toUpperCase());
        }
        return line;
    }

    public static String isExclusivePropertyString(String[] listOfProperties, String line) {
        String lineExclusiveProperty = "";
        int first = 0, second = 0, third = 0, forth = 0, fifth = 0, sixth = 0, seventh = 0,
                eight = 0, ninth = 0, tenth = 0, eleventh = 0, twelve = 0, thirteen = 0, fourteen = 0;
        String firstProp = "";
        StringBuilder secondProp = new StringBuilder();
        StringBuilder thirdP = new StringBuilder();
        StringBuilder forthP = new StringBuilder();
        StringBuilder fifthP = new StringBuilder();
        StringBuilder sixthP = new StringBuilder();
        StringBuilder seventhP = new StringBuilder();
        StringBuilder eightP = new StringBuilder();
        StringBuilder ninthP = new StringBuilder();
        StringBuilder tenthP = new StringBuilder();
        StringBuilder eleventhP = new StringBuilder();
        StringBuilder twelveP = new StringBuilder();
        StringBuilder thirteenP = new StringBuilder();
        StringBuilder fourteenP = new StringBuilder();
        for (int i = 2; i < listOfProperties.length; i++) {

            if (listOfProperties[i].equalsIgnoreCase("even") ||
                    listOfProperties[i].equalsIgnoreCase("odd") ||
                    listOfProperties[i].equalsIgnoreCase("-even") ||
                    listOfProperties[i].equalsIgnoreCase("-odd")) {
                first++;
                if (first == 1)
                    firstProp = listOfProperties[i];
                if (first == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (firstProp + ", " + secondPropInn).toUpperCase();
                }
            } else if (line.contains("duck")
                    && line.contains("spy")) {
                second++;
                if (second == 1)
                    secondProp.append(listOfProperties[i]);
                if (second == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (secondProp + ", " + secondPropInn).toUpperCase();
                }

            } else if ((listOfProperties[i].equalsIgnoreCase("-duck")
                    || listOfProperties[i].equalsIgnoreCase("duck"))) {
                third++;
                if (third == 1)
                    thirdP.append(listOfProperties[i]);
                if (third == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (thirdP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("-spy")
                    || listOfProperties[i].equalsIgnoreCase("spy"))) {
                forth++;
                if (forth == 1)
                    forthP.append(listOfProperties[i]);
                if (forth == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (forthP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((line.contains("sunny")
                    && line.contains("square"))) {
                fifth++;

                if (fifth == 1)
                    fifthP.append(listOfProperties[i]);
                if (fifth == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (fifthP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("-sunny")
                    || listOfProperties[i].equalsIgnoreCase("sunny"))) {
                sixth++;

                if (sixth == 1)
                    sixthP.append(listOfProperties[i]);
                if (sixth == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (sixthP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("square")
                    || listOfProperties[i].equalsIgnoreCase("-square"))) {
                seventh++;
                if (seventh == 1)
                    seventhP.append(listOfProperties[i]);
                if (seventh == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (seventhP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((line.contains("sad")
                    && line.contains("happy"))) {
                eight++;

                if (eight == 1)
                    eightP.append(listOfProperties[i]);
                if (eight == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (eightP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("-sad")
                    || listOfProperties[i].equalsIgnoreCase("sad"))) {
                ninth++;

                if (ninth == 1)
                    ninthP.append(listOfProperties[i]);
                if (ninth == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (ninthP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("happy")
                    || listOfProperties[i].equalsIgnoreCase("-happy"))) {
                tenth++;

                if (tenth == 1)
                    tenthP.append(listOfProperties[i]);
                if (tenth == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (tenthP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("palindromic")
                    || listOfProperties[i].equalsIgnoreCase("-palindromic"))) {
                eleventh++;

                if (eleventh == 1)
                    eleventhP.append(listOfProperties[i]);
                if (eleventh == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (eleventhP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("gapful")
                    || listOfProperties[i].equalsIgnoreCase("-gapful"))) {
                twelve++;

                if (twelve == 1)
                    twelveP.append(listOfProperties[i]);
                if (twelve == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (twelveP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("jumping")
                    || listOfProperties[i].equalsIgnoreCase("-jumping"))) {
                thirteen++;

                if (thirteen == 1)
                    thirteenP.append(listOfProperties[i]);
                if (thirteen == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (thirteenP + ", " + secondPropInn).toUpperCase();
                }
            } else if ((listOfProperties[i].equalsIgnoreCase("buzz")
                    || listOfProperties[i].equalsIgnoreCase("-buzz"))) {
                fourteen++;

                if (fourteen == 1)
                    fourteenP.append(listOfProperties[i]);
                if (fourteen == 2) {
                    String secondPropInn = listOfProperties[i];
                    lineExclusiveProperty = (fourteenP + ", " + secondPropInn).toUpperCase();
                }
            }
        }

        return lineExclusiveProperty;
    }

    public static boolean isMistakenWordOrWords(String[] array) {
        int count = 0;
        for (int i = 2; i < array.length; i++) {
            String property = array[i];
            if (array[i].startsWith("-")) {
                property = property.replace("-", "").trim();
            }
            if (Arrays.toString(Parameters.values()).contains(property.toUpperCase())) {
                count++;
            }
        }
        return count == array.length - 2;
    }

    public static long[] splitDigits(long number) {
        // Определяем количество цифр в числе
        int digitCount = String.valueOf(number).length();

        // Создаем массив для хранения отдельных цифр
        long[] digits = new long[digitCount];

        // Разделяем цифры и сохраняем их в массиве
        int index = digitCount - 1; // Начинаем с последней позиции в массиве
        while (number > 0) {
            long digit = number % 10; // Получаем последнюю цифру
            digits[index] = digit; // Сохраняем цифру в массив
            number /= 10; // Уменьшаем число на одну цифру
            index--; // Переходим к предыдущей позиции в массиве
        }

        return digits;
    }

    public static boolean isHappy(long number) {
        int length = splitDigits(number).length;
        long[] array = splitDigits(number);
        long sum = 0;
        while (sum != 1) {
            if (sum == 4) {
                break; // Выход из цикла, если число повторяется
            }
            sum = 0;
            for (int i = 0; i < length; i++) {
                long digit = array[i];
                sum += digit * digit;
            }
            length = splitDigits(sum).length;
            array = splitDigits(sum);

        }
        return sum == 1;
    }

    public static boolean isSad(long number) {
        return !isHappy(number);
    }

    public static boolean checkAllProperties1(String[] array, long firstDigit) {
        for (int i = 2; i < array.length; i++) {
            if (!array[i].startsWith("-") && !isProperty(array[i], firstDigit)) {
                return false; // Если хотя бы одно свойство не соответствует, возвращаем false
            }
            if (array[i].startsWith("-")) {
                String property = array[i].substring(1);
                if (isProperty(property, firstDigit)) {
                    return false;
                }
            }
        }
        return true; // Если все свойства соответствуют, возвращаем true
    }

    public static String[] removeDuplicatesFromPosition(String[] array) {
        // Добавляем строки до указанной позиции во множество
        Set<String> uniqueSet = new LinkedHashSet<>(Arrays.asList(array));
        return uniqueSet.toArray(new String[0]);
    }

}
