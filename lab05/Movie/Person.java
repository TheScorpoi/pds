
/*
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Person {
    private String fisrtName;
    private String latName;
    
    public Person(String fisrtName, String latName) {
        this.fisrtName = fisrtName;
        this.latName = latName;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLatName() {
        return latName;
    }

    public void setLatName(String latName) {
        this.latName = latName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fisrtName == null) ? 0 : fisrtName.hashCode());
        result = prime * result + ((latName == null) ? 0 : latName.hashCode());
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
        Person other = (Person) obj;
        if (fisrtName == null) {
            if (other.fisrtName != null)
                return false;
        } else if (!fisrtName.equals(other.fisrtName))
            return false;
        if (latName == null) {
            if (other.latName != null)
                return false;
        } else if (!latName.equals(other.latName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return fisrtName + " " +latName;
    }
}
