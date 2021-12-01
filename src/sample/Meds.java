package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Meds {
    private StringProperty Mname;
    private StringProperty dosg;
    private StringProperty duran;

    public Meds(String Mname, String dosg, String duran) {

        this.Mname = new SimpleStringProperty(Mname);
        this.dosg = new SimpleStringProperty(dosg);
        this.duran = new SimpleStringProperty(duran);
    }
    /*
    public String getMname() {
        return Mname.get();
    }

    public String getDosg() {
        return dosg.get();
    }

    public String getDuran() {
        return duran.get();
    }

    public void setMname(String value) {
        Mname.setValue(value);
    }

    public void setDosg(String value) {
        dosg.setValue(value);
    }

    public void setDuran(String value) {
        duran.setValue(value);
    }
*/
    public StringProperty MnameProperty() {
        return Mname;
    }

    public void setMnameProperty(String Mname) {
        this.Mname = new SimpleStringProperty(Mname);
    }

    public StringProperty dosgProperty() {
        return dosg;
    }

    public void setdosgProperty(String dosg) {
        this.dosg = new SimpleStringProperty(dosg);
    }

    public StringProperty duranProperty() {
        return duran;
    }

    public void setduranProperty(String duran) {
        this.duran = new SimpleStringProperty(duran);
    }
}
