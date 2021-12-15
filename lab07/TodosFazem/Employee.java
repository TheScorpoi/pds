package TodosFazem;

import java.util.Date;

public class Employee implements EmployeeInterface {

    private String name;
    private int num_emp;
    private Date startDate;
    private Date finishDate;

    public Employee(String name, int num_emp) {
        this.name = name;
        this.num_emp = num_emp;
    }

    //the methods above are @deprecated, but the guide tell to use Date class, that are deprecated, instead of Calendar, so we use Date class 
    @Override
    public void start(Date date) {
        this.startDate = date;
        System.out.println(this.toString() + "\nComeçou a trabalhar, " + startDate.getDate() + "/" + (startDate.getMonth() + 1) + "  "+ startDate.getHours() + ":" + startDate.getMinutes());
    }

    @Override
    public void terminate(Date date) {
        this.finishDate = date;
        //in finish.getMinutes() there are a sum, just to make the "time travel" more realistic, but after 47 minutes, it'll sum that with results more that 60 minutes
        System.out.println("Acabou de trabalhar, " + finishDate.getDate() + "/" + (finishDate.getMonth() + 1) + "  "+ finishDate.getHours() + ":" + (finishDate.getMinutes() + 14) + "\n");
    }

    @Override
    public void work() {
        System.out.println("A trabalhar...");
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_emp() {
        return num_emp;
    }

    public void setNum_emp(int num_emp) {
        this.num_emp = num_emp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + num_emp;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (num_emp != other.num_emp)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name + " , " + num_emp;
    }

}
