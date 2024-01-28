import java.util.Scanner;

public class numToRoman {

    public static String convertToRoman(int n) {
	    String rom = "";
	    
	    int tho = n/1000;
	    if (tho != 0) {
	        while (n >= 1000) {
	            rom += "M";
	            n -= 1000;
	        }
	    }
	    if (n >= 900 && n < 1000) {
	        rom += "CM";
	        n -= 900;
	    }
	    
	    int fhun = n/500;
	    if (fhun != 0) {
	        while (n >= 500) {
	            rom += "D";
	            n -= 500;
	        }
	    }
	    if (n >= 400 && n < 500) {
	        rom += "CD";
	        n -= 400;
	    }
	    
	    int hun = n/100;
	    if (hun != 0) {
	        while (n >= 100) {
	            rom += "C";
	            n -= 100;
	        }
	    }
	    if (n >= 90 && n < 100) {
	        rom += "XC";
	        n -= 90;
	    }
	    
	    int fif = n/50;
	    if (fif != 0) {
	        while (n >= 50) {
	            rom += "L";
	            n -= 50;
	        }
	    }
	    if (n >= 40 && n < 50) {
	        rom += "XL";
	        n -= 40;
	    }
	    
	    int ten = n/10;
	    if (ten != 0) {
	        while (n >= 10) {
	            rom += "X";
	            n -= 10;
	        }
	    }
	    if (n >= 9 && n < 10) {
	        rom += "IX";
	        n -= 9;
	    }
	    
	    int fiv = n/5;
	    if (fiv != 0) {
	        while (n >= 5) {
	            rom += "V";
	            n -= 5;
	        }
	    }
	    if (n >= 4 && n < 5) {
	        rom += "IV";
	        n -= 4;
	    }
	    
	    int one = n/1;
	    if (one != 0) {
	        while(n >= 1) {
	            rom += "I";
	            n -= 1;
	        }
	    }
	    return rom;
	}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        convertToRoman(num);

        sc.close();
        
    }
}