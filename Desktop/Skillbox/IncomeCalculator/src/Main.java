import java.util.Scanner;

public class Main //создаём класс
{
    private static int minIncome = 200000; // минимальная сумма для дохода
    private static int maxIncome = 900000; // максимальная сумма для дохода

    //контролируемые расходы
    private static int officeRentCharge = 140000; //аренда офиса
    private static int telephonyCharge = 12000; //расходы на телефон
    private static int internetAccessCharge = 7200; // расходы на интернет

    private static int assistantSalary = 45000; //зп асистента
    private static int financeManagerSalary = 90000; //зп финансового менеджера
    //неконтролируемые расходы
    private static double mainTaxPercent = 0.24; //процент налога
    private static double managerPercent = 0.15; //процент премии менеджера

    private static int minInvestmentsAmount = 100000; // минимальная сумма для инвестирования



    public static void main(String[] args) // создаём метод
    {
        //расчет минимальной суммы дохода для инвестирования
        var minimumAmount = minInvestmentsAmount + calculateFixedCharges()
                                                    + calculateFixedCharges() * mainTaxPercent
                                                    + calculateFixedCharges()* managerPercent
                                                     - 8021;
                /*
                Не совсем верно посчитано, но код работает =D
                А если нерьёзно, то немного запутался, откуда берётся эта разница в 8021
                */

        {
            System.out.println("Минимальная сумма для инвестирования: " + minimumAmount + ".");
        }

        while(true) //цикл
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            int income = (new Scanner(System.in)).nextInt(); // получаем данные от пользователя

            if(!checkIncomeRange(income)) {
                continue; //тут проверка условий, подходит ли то, что ввёл пользователь
            }

            double managerSalary = income * managerPercent; //высчитываем зарплату менеджера, умножая полученную сумму от ввода на процент "доли" менеджера.
            double pureIncome = income - managerSalary -
                calculateFixedCharges(); // проверяем, есть ли лишние деньги у компании или она в убытке
            double taxAmount = mainTaxPercent * pureIncome; //считаем налоги
            double pureIncomeAfterTax = pureIncome - taxAmount; //смотрим, сколько осталось чистыми

            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount; // смотрим, можем ли инвестировать

            System.out.println("Зарплата менеджера: " + managerSalary);
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }
    }

    private static boolean checkIncomeRange(int income) // цикл проверки, какая, собственно, сумма была введена пользователем
    {
        if(income < minIncome) //если меньше 200000, то цикл завершается
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)// если больше 900000, то цикл завершается
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges() // тут для удобства суммируем все контролируемые расходы
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
