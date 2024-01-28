class Student {
    String name; int grade; char section; int percent;

    public void printInfo() {
        System.out.println(this.name);
        System.out.println(this.grade+"-"+this.section);
        System.out.println(this.percent+"%");
    }

    public void printInfo(String name) {
        System.out.println(name);
    }
    public void printInfo(int grade) {
        System.out.println(grade);
    }
    public void printInfo(Float percent) {
        System.out.println(percent);
    }

    Student(String stuName, int stuGrade, char stuSec, int stuPercet) {
        this.name = stuName;
        this.grade = stuGrade;
        this.section = stuSec;
        this.percent = stuPercet;
    }

    Student(Student s2) {
        this.name = s2.name;
        this.grade = s2.grade;
        this.section = s2.section;
        this.percent = s2.percent;
    }

}

class Clothes{
    String code; String type; String fabric; int price;

    public void printInfo() {
        System.out.println(this.code);
        System.out.println(this.type);
        System.out.println("Rs."+this.price);
    }
}

public class OOPS {
    public static void main(String[] args) {

        Student s1 = new Student("Adwaiy", 12, 'C', 100);

        Student s2 = new Student(s1);
        s2.printInfo();
        
        // Clothes c1 = new Clothes();
        // c1.printInfo();
    }
}
