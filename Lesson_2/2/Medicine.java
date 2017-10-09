import java.util.Scanner;
import java.util.HashSet;

public class Medicine{
public static void main(String[] args){
Patient sysEngineer = new Patient("Bob", "Smith", "Lowet");
IdCard card = new IdCard(56);
}
}

class Man{
protected String name;
protected String surname;
protected String patronymic;

public void setName(String newName){
name = newName;
}

public String getName(){
return name;
}

public void setSurname(String newName){
surname = newName;
}

public String getSurname(){
return surname;
}

public void setPatronymic(String newName){
patronymic = newName;
}

public String getPatronymic(){
return patronymic;
}
}


class Patient extends Man{
private IdCard iCard;

public Patient(String n, String s, String p){
name = n;
surname = s;
patronymic = p;
}

public void setIdCard(IdCard c){
iCard = c;
}

public IdCard getIdCard(){
return iCard;
}
}


class IdCard implements Diseases{
private static Scanner in = new Scanner(System.in);
private int number;
private HashSet<String> Disease = new HashSet<>();

public IdCard(int n){
number = n;
}

public void setNumber(int newNumber){
number = newNumber;
}

public int getNumber(){
return number;
}

public void addDisease(){
String newDisease = in.next();
Disease.add(newDisease);
}

public HashSet getDisease(){
return Disease;
}

public void removeDisease(){
String deleteDisease = in.next();
Disease.remove(deleteDisease);
}

public int getDiseaseCount(){
return getDisease().size();
}
}

interface Diseases{
int getDiseaseCount();
}
