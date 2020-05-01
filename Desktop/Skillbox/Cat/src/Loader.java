
public class Loader
{
    public static void main ( String[] args )
    {
        Cat tom = new Cat();
        Cat murka = new Cat();
        Cat vasya = new Cat();
        Cat coom = new Cat();
        Cat moon = new Cat();

        //вес кошек
        System.out.println("Вес Тома: " + tom.getWeight() + "\n"
                + "Вес Мурки: " + murka.getWeight() + "\n"
                + "Вес Васи: " + vasya.getWeight() + "\n"
                + "Вес Кумы: " + coom.getWeight() + "\n"
                + "Вес Луны: " + moon.getWeight());
        //кормим две кошки
        moon.eat();
       // System.out.println(moon.meatSumm);
        coom.eat();
        System.out.println(" " + "\n" + "" + "Вес Кумы после еды: " + coom.getWeight() + "\n"
                + "Вес Луны после еды: " + moon.getWeight());


        //перекармливаем кошку

        for (var i = moon.getWeight(); i <=9000;i ++ )
            {
             System.out.println(moon.getWeight() + " "); //можно и без этой строки, чтобы не выводить каждый раз вес Луны

             if (moon.getWeight() <= 9000)
             {
            moon.eat();


            System.out.println(moon.getStatus() + " Луна");
             }
            }


       // String t;
        //замяукиваем кошку


        }
        /**for (var j = coom.getWeight(); j >= 1000; j--)
            {
             System.out.println(coom.getWeight() + " "); //можно и без этой строки, чтобы не выводить каждый раз вес Кумы

        if (coom.getWeight() >= 1000)
             {
               coom.meow();
             }

            System.out.println(coom.getStatus() + " Кума");
             }**/
        //кормим кошку и вызываем метод pee()

    }


