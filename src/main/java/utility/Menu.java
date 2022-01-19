package utility;

public class Menu {
    private static String dotted = "---------------------------------------------------";
    public static void loginMenu(){
        System.out.println("1:Login");
        System.out.println("2:Sing up");
        System.out.println("3:Exit");
        System.out.println(dotted);

    }

    public static void clerkMenu(){
        System.out.println();
        System.out.println("1:Add Customer");
        System.out.println("2:Add Account Customer");
        System.out.println("3:show trancaction");
        System.out.println("4:show date transaction account id");
        System.out.println("5:show customer accounts");
        System.out.println("6:Exit");
        System.out.println(dotted);
    }
    public static void customerMenu()
    {
        System.out.println();
        System.out.println("1:Add transaction");
        System.out.println("2:tranaction");
        System.out.println("3:Edit password");
        System.out.println("1:Add Customer");
        System.out.println("5:Exit");
    }


}
