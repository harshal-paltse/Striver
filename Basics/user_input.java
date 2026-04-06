import java.util.Scanner;
public class user_input{
    public static void main(String[] args){
        System.out.println("Enter The Number : ");
        Scanner sc = new Scanner(System.in);
        int roll = sc.nextInt();
        System.out.println("Your Roll Number is : " + roll);
    }
}