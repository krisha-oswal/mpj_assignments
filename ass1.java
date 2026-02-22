
class Employee {
    double salary;

 
    Employee(double salary) {
        this.salary = salary;
    }


    void displaySalary() {
        System.out.println("Salary: " + salary);
    }
}


class FullTimeEmployee extends Employee {

    FullTimeEmployee(double salary) {
        super(salary);
    }

    // 50% hike
    void calculateSalary() {
        salary = salary + (salary * 0.50);
    }
}

// Derived class 2
class InternEmployee extends Employee {

    InternEmployee(double salary) {
        super(salary);
    }


    void calculateSalary() {
        salary = salary + (salary * 0.25);
    }
}

public class HierarchicalInheritanceDemo {
    public static void main(String[] args) {

        FullTimeEmployee fte = new FullTimeEmployee(40000);
        System.out.println("Full Time Employee:");
        System.out.print("Before Hike - ");
        fte.displaySalary();
        fte.calculateSalary();
        System.out.print("After Hike - ");
        fte.displaySalary();

        System.out.println();

        
        InternEmployee intern = new InternEmployee(20000);
        System.out.println("Intern Employee:");
        System.out.print("Before Hike - ");
        intern.displaySalary();
        intern.calculateSalary();
        System.out.print("After Hike - ");
        intern.displaySalary();
    }
}
