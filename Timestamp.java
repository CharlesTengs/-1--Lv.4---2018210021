import java.util.Scanner;

public class Timestamp {
    private static int year = 0;    //成员变量：写在类里，方法外
    private static int month = 0;   //类的封装：声明变量时加private，这里略去从类外部访问变量的方法
    private static int day = 0;
    private static int hour = 0;
    private static int minute = 0;
    private static long second;
    private static long relativeTimestamp;
    private static long absoluteTimestamp;
    static final long COMMON_YEAR = 31536000;
    static final long LEAP_YEAR = 31622400;
    static final long TIME_BEFORE_1970 = 1492 * COMMON_YEAR + 477 * LEAP_YEAR;
    static final int BIG_MONTH = 2678400;
    static final int SMALL_MONTH = 2592000;
    static final int BIG_FEBRUARY = 2505600;
    static final int SMALL_FEBRUARY = 2419200;
    static final int A_DAY = 86400;
    static final int AN_HOUR = 3600;
    static final int A_MINUTE = 60;


    public Timestamp() {     //空构造方法

    }

    public static void main(String[] args) {
        test1(args);
        System.out.println("");
        test2(args);
    }

    public static void test1(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Give me a timestamp and you will know the time it represents.");
        String a = in.nextLine();
        long relativeTimestamp = Long.parseLong(a);
        long absoluteTimestamp = relativeTimestamp + TIME_BEFORE_1970;  //转化成从公元元年1月1日0时0分0秒到输入时间点的总秒数，方便后续操作
        while (absoluteTimestamp >= 0) {                                //弊端是计算机要多算许多步骤
            year++;
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                absoluteTimestamp -= LEAP_YEAR;
            } else {
                absoluteTimestamp -= COMMON_YEAR;
            }
        }
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {  //判断是否是闰年
            absoluteTimestamp += LEAP_YEAR;
        } else {
            absoluteTimestamp += COMMON_YEAR;
        }
        while (absoluteTimestamp >= 0) {
            month++;
            if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
                absoluteTimestamp -= BIG_MONTH;
            } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                absoluteTimestamp -= SMALL_MONTH;
            } else if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                absoluteTimestamp -= BIG_FEBRUARY;
            } else {
                absoluteTimestamp -= SMALL_FEBRUARY;
            }
        }
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
            absoluteTimestamp += BIG_MONTH;
        } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            absoluteTimestamp += SMALL_MONTH;
        } else if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            absoluteTimestamp += BIG_FEBRUARY;
        } else {
            absoluteTimestamp += SMALL_FEBRUARY;
        }
        while (absoluteTimestamp >= 0) {
            day++;
            absoluteTimestamp -= A_DAY;
        }

        absoluteTimestamp += A_DAY;
        while (absoluteTimestamp >= 0) {
            hour++;
            absoluteTimestamp -= AN_HOUR;
        }

        absoluteTimestamp += AN_HOUR;
        hour--;
        while (absoluteTimestamp >= 0) {
            minute++;
            absoluteTimestamp -= A_MINUTE;
        }
        minute--;

        absoluteTimestamp += A_MINUTE;
        second = absoluteTimestamp;
        System.out.print("Greenwich Mean Time");
        System.out.print(year + "/");
        System.out.print(month + "/");
        System.out.print(day + " ");
        System.out.print(hour + ":");
        System.out.print(minute + ":");
        System.out.print(second);
    } //时间戳转时间

    public static void test2(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("We can do that in an opposite way. Just follow my guide, input a specific timing, then you will see the timestamp of it.");
        System.out.println("Year:");
        String a = in.nextLine();
        year = Integer.parseInt(a);
        System.out.println("Month:");
        String b = in.nextLine();
        month = Integer.parseInt(b);
        System.out.println("Day:");
        String c = in.nextLine();
        day = Integer.parseInt(c);
        System.out.println("Hour:");
        String d = in.nextLine();
        hour = Integer.parseInt(d);
        System.out.println("Minute:");
        String e = in.nextLine();
        minute = Integer.parseInt(e);
        System.out.println("Second:");
        String f = in.nextLine();
        second = Integer.parseInt(f);
        year--;
        int x = year / 4;
        int y = year / 100;
        int z = year / 400;
        int sum = x - y + z;
        absoluteTimestamp = COMMON_YEAR * (year - sum) + LEAP_YEAR * sum;
        int i = 0;
        while (i < month - 1) {
            i++;
            if ((i == 1) || (i == 3) || (i == 5) || (i == 7) || (i == 8) || (i == 10) || (i == 12)) {
                absoluteTimestamp += BIG_MONTH;
            } else if ((i == 4) || (i == 6) || (i == 9) || (i == 11)) {
                absoluteTimestamp += SMALL_MONTH;
            } else if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                absoluteTimestamp += BIG_FEBRUARY;
            } else {
                absoluteTimestamp += SMALL_FEBRUARY;
            }
        }
        int j = 0;
        while (j < day - 1) {
            j++;
            absoluteTimestamp += A_DAY;
        }
        int m = 0;
        while (m < hour) {
            m++;
            absoluteTimestamp += AN_HOUR;
        }
        int n = 0;
        while (n < minute) {
            n++;
            absoluteTimestamp += A_MINUTE;
        }
        absoluteTimestamp += second;
        relativeTimestamp = absoluteTimestamp - TIME_BEFORE_1970;//转换成相对于1970年1月1日0时0点的时间戳
        System.out.println(relativeTimestamp);


    } //时间转时间戳


}
