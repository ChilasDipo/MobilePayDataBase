import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
public class Controller {

    @FXML
    TextArea textblock, textblock2;
    @FXML
    TextField name, number, adresse ,card, fra,til,belob;
    DataBaseQeries dataBaseQeries = new DataBaseQeries();
    @FXML
    void connect(){
        ArrayList templist = new ArrayList();
        String[] tempstring;
        tempstring = dataBaseQeries.selectallnames();
      // dataBaseQeries.selectallnames();
        textblock.clear();
        for (int i = 0; i <tempstring.length ; i++) {
            if (tempstring[i] != null)
            textblock.appendText(tempstring[i]);
            textblock.appendText("\n");
        }
    }
    @FXML
    void adduser(){
        String sname = name.getText();
        int snumber = Integer.parseInt(number.getText());
        String sadresse = adresse.getText();
        int scard = Integer.parseInt(card.getText());
        dataBaseQeries.adduser(sname,snumber,sadresse,scard);
    }

    @FXML
    void movemoney(){
        String sfra = fra.getText();
        String stil = til.getText();
        int beloben = Integer.parseInt(belob.getText());
        dataBaseQeries.movemoney(stil,sfra,beloben);
    }
    @FXML
    void getTransactionInfo(){
        String[] tilarray = dataBaseQeries.getTranaction(1);
        String[] fraarray = dataBaseQeries.getTranaction(2);
        int[] belobarray = dataBaseQeries.getAmountTranaction();
        textblock2.clear();
        for (int i = 0; i <tilarray.length ; i++) {
            if (tilarray[i] != null){
                textblock2.appendText(" til = "+ tilarray[i] + "  fra = " + fraarray[i] + " mændge =  " + belobarray[i]);
                textblock2.appendText("\n");
            }
        }
    }
}
