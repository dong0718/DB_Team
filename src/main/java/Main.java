import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("원하는 대분류 기능 선택");

        Scanner sc= new Scanner(System.in);

        int startmenu=sc.nextInt();

        switch (startmenu)
        {
            case 1:
                System.out.println("원하는 소분류 기능 선택");
                int firstmenu=sc.nextInt();
                switch (firstmenu)
                {

                }
                break;
            case 2:
                System.out.println("원하는 소분류 기능 선택");
                int secondmenu=sc.nextInt();
                switch (secondmenu)
                {

                }
                break;
            case 3:
                System.out.println("원하는 소분류 기능 선택");
                int thirdmenu=sc.nextInt();
                switch (thirdmenu)
                {

                }
                break;
            case 4:
                System.out.println("원하는 소분류 기능 선택");
                int fourthmenu=sc.nextInt();
                switch (fourthmenu)
                {

                }
                break;
        }
    }

}
