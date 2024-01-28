import java.util.Scanner;

/*
IMP. 
    int bit = x & (1 << (i-1));     // gets bit at ith position of int x
    y = y | (1 << (i-1));       // puts bit 1 at ith pos of int y
*/

public class bitManipulation {

    // to get the bit at nth position; bitmask: 1<<i and operation: & (bit and)
    public static int getBit(int num, int pos) {
        int bitMask = 1<<pos;
        if ((bitMask & num) == 0) {
            return 0;
        }
        return 1;
    }

    // to make the nth bit 1; bitmask: 1<<i and op: | (bit or)
    public static int setBit(int num, int pos) {
        int bitMask = 1<<pos;
        int newNum = bitMask | num;
        return newNum;
    }

    // to make the nth bit 0; bitmask: 1<<i and op: 1) ~ (not), 2) & (bit and)
    public static int clearBit(int num, int pos) {
        int bitMask = 1<<pos;
        int notBitMask = ~bitMask;
        int newNum = notBitMask & num;
        return newNum;
    }

    // to change bit at nth bit acc. to user
    public static int updateBit(int num, int pos, int op) {
        if (op == 1) {
            // set condition
            return setBit(num, pos);
        } else {
            // clear condition
            return clearBit(num, pos);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int num = sc.nextInt(); //Eg. 5 -> bin 0101
        System.out.print("Enter position: ");
        int pos = sc.nextInt(); //Eg. 1 -> bitmask 0010
        System.out.print("Enter update operation(1 or 0): ");
        int op = sc.nextInt(); //Eg. 1 -> set x = 1

        int newNum = 0;

        // newNum = getBit(num, pos);
        // newNum = setBit(num, pos);
        // newNum = clearBit(num, pos);
        newNum = updateBit(num, pos, op); //Eg. 7 -> bin 0111

        System.out.println(newNum);

        sc.close();
    }    
}
