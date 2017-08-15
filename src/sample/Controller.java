package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.omg.CORBA.NameValuePair;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    ScrollPane sp = new ScrollPane();
    @FXML
    HBox cont=new HBox();
    // Fiat currency controls
    @FXML ToggleGroup grp=new ToggleGroup();
    @FXML
    RadioButton rb1=new RadioButton();
    @FXML
     RadioButton rb2=new RadioButton();
    @FXML
    RadioButton rb3=new RadioButton();
    @FXML
    RadioButton rb4=new RadioButton();
    // End of fiat currency controls

    //Crypto Json urls
    String btc_url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,EUR,RUB,GBP";
    String ltc_url = "https://min-api.cryptocompare.com/data/price?fsym=LTC&tsyms=USD,EUR,RUB,GBP";
    String ant_url = "https://min-api.cryptocompare.com/data/price?fsym=ANT&tsyms=USD,EUR,RUB,GBP";
    String bat_url = "https://min-api.cryptocompare.com/data/price?fsym=BAT&tsyms=USD,EUR,RUB,GBP";
    String bnt_url = "https://min-api.cryptocompare.com/data/price?fsym=BNT&tsyms=USD,EUR,RUB,GBP";
    String blk_url = "https://min-api.cryptocompare.com/data/price?fsym=BLK&tsyms=USD,EUR,RUB,GBP";
    String bts_url = "https://min-api.cryptocompare.com/data/price?fsym=BTS&tsyms=USD,EUR,RUB,GBP";
    String cvc_url = "https://min-api.cryptocompare.com/data/price?fsym=CVC&tsyms=USD,EUR,RUB,GBP";
    String dcr_url = "https://min-api.cryptocompare.com/data/price?fsym=DCR&tsyms=USD,EUR,RUB,GBP";
    String dgd_url = "https://min-api.cryptocompare.com/data/price?fsym=DGD&tsyms=USD,EUR,RUB,GBP";
    String edg_url = "https://min-api.cryptocompare.com/data/price?fsym=EDG&tsyms=USD,EUR,RUB,GBP";
    String eos_url = "https://min-api.cryptocompare.com/data/price?fsym=EOS&tsyms=USD,EUR,RUB,GBP";
    String fun_url = "https://min-api.cryptocompare.com/data/price?fsym=FUN&tsyms=USD,EUR,RUB,GBP";
    String gam_url = "https://min-api.cryptocompare.com/data/price?fsym=GAME&tsyms=USD,EUR,RUB,GBP";
    String gno_url = "https://min-api.cryptocompare.com/data/price?fsym=GNO&tsyms=USD,EUR,RUB,GBP";
    String gnt_url = "https://min-api.cryptocompare.com/data/price?fsym=GNT&tsyms=USD,EUR,RUB,GBP";
    String gup_url = "https://min-api.cryptocompare.com/data/price?fsym=GUP&tsyms=USD,EUR,RUB,GBP";
    String icn_url = "https://min-api.cryptocompare.com/data/price?fsym=ICN&tsyms=USD,EUR,RUB,GBP";
    String kmd_url = "https://min-api.cryptocompare.com/data/price?fsym=KMD&tsyms=USD,EUR,RUB,GBP";
    String lbc_url = "https://min-api.cryptocompare.com/data/price?fsym=LBC&tsyms=USD,EUR,RUB,GBP";
    String mln_url = "https://min-api.cryptocompare.com/data/price?fsym=MLN&tsyms=USD,EUR,RUB,GBP";
    String mon_url = "https://min-api.cryptocompare.com/data/price?fsym=MONA&tsyms=USD,EUR,RUB,GBP";
    String nmc_url = "https://min-api.cryptocompare.com/data/price?fsym=NMC&tsyms=USD,EUR,RUB,GBP";
    String nmr_url = "https://min-api.cryptocompare.com/data/price?fsym=NMR&tsyms=USD,EUR,RUB,GBP";
    String omg_url = "https://min-api.cryptocompare.com/data/price?fsym=OMG&tsyms=USD,EUR,RUB,GBP";
    String rep_url = "https://min-api.cryptocompare.com/data/price?fsym=REP&tsyms=USD,EUR,RUB,GBP";
    String rdd_url = "https://min-api.cryptocompare.com/data/price?fsym=RDD&tsyms=USD,EUR,RUB,GBP";
    String rlc_url = "https://min-api.cryptocompare.com/data/price?fsym=RLC&tsyms=USD,EUR,RUB,GBP";
    String sc_url = "https://min-api.cryptocompare.com/data/price?fsym=SC&tsyms=USD,EUR,RUB,GBP";
    String snt_url = "https://min-api.cryptocompare.com/data/price?fsym=SNT&tsyms=USD,EUR,RUB,GBP";
    String start_url = "https://min-api.cryptocompare.com/data/price?fsym=START&tsyms=USD,EUR,RUB,GBP";
    String vox_url = "https://min-api.cryptocompare.com/data/price?fsym=VOX&tsyms=USD,EUR,RUB,GBP";
    String vrc_url = "https://min-api.cryptocompare.com/data/price?fsym=VRC&tsyms=USD,EUR,RUB,GBP";
    String vtc_url = "https://min-api.cryptocompare.com/data/price?fsym=VTC&tsyms=USD,EUR,RUB,GBP";
    String waves_url = "https://min-api.cryptocompare.com/data/price?fsym=WAVES&tsyms=USD,EUR,RUB,GBP";
    String wings_url = "https://min-api.cryptocompare.com/data/price?fsym=WINGS&tsyms=USD,EUR,RUB,GBP";
    String xmr_url = "https://min-api.cryptocompare.com/data/price?fsym=XMR&tsyms=USD,EUR,RUB,GBP";
    String zec_url = "https://min-api.cryptocompare.com/data/price?fsym=ZEC&tsyms=USD,EUR,RUB,GBP";


    //End crypto Json urls

    //BTC controls
    @FXML TextField btcu=new TextField();
    @FXML TextField btcfiat=new TextField();
    //End of BTC controls

    //LTC controls
    @FXML TextField ltcu=new TextField();
    @FXML TextField ltcfiat=new TextField();
    //End of LTC controls

    //ANT controls
    @FXML TextField antu=new TextField();
    @FXML TextField antfiat=new TextField();
    //End of ANT controls

    //BAT controls
    @FXML TextField batu=new TextField();
    @FXML TextField batfiat=new TextField();
    //End of BAT controls

    //BNT controls
    @FXML TextField bntu=new TextField();
    @FXML TextField bntfiat=new TextField();
    //End of BNT controls

    //BLK controls
    @FXML TextField blku=new TextField();
    @FXML TextField blkfiat=new TextField();
    //End of BLK controls

    //BTS controls
    @FXML TextField btsu=new TextField();
    @FXML TextField btsfiat=new TextField();
    //End of BTS controls

    //CVC controls
    @FXML TextField cvcu=new TextField();
    @FXML TextField cvcfiat=new TextField();
    //End of CVC controls

    //DCR controls
    @FXML TextField dcru=new TextField();
    @FXML TextField dcrfiat=new TextField();
    //End of DCR controls

    //DGD controls
    @FXML TextField dgdu=new TextField();
    @FXML TextField dgdfiat=new TextField();
    //End of DGD controls

    //EDG controls
    @FXML TextField edgu=new TextField();
    @FXML TextField edgfiat=new TextField();
    //End of EDG controls

    //EOS controls
    @FXML TextField eosu=new TextField();
    @FXML TextField eosfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField funu=new TextField();
    @FXML TextField funfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField gamu=new TextField();
    @FXML TextField gamfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField gnou=new TextField();
    @FXML TextField gnofiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField gntu=new TextField();
    @FXML TextField gntfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField gupu=new TextField();
    @FXML TextField gupfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField icnu=new TextField();
    @FXML TextField icnfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField kmdu=new TextField();
    @FXML TextField kmdfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField lbcu=new TextField();
    @FXML TextField lbcfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField mlnu=new TextField();
    @FXML TextField mlnfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField monu=new TextField();
    @FXML TextField monfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField nmcu=new TextField();
    @FXML TextField nmcfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField nmru=new TextField();
    @FXML TextField nmrfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField omgu=new TextField();
    @FXML TextField omgfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField repu=new TextField();
    @FXML TextField repfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField rddu=new TextField();
    @FXML TextField rddfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField rlcu=new TextField();
    @FXML TextField rlcfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField scu=new TextField();
    @FXML TextField scfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField sntu=new TextField();
    @FXML TextField sntfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField startu=new TextField();
    @FXML TextField startfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField voxu=new TextField();
    @FXML TextField voxfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField vrcu=new TextField();
    @FXML TextField vrcfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField vtcu=new TextField();
    @FXML TextField vtcfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField wavesu=new TextField();
    @FXML TextField wavesfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField wingsu=new TextField();
    @FXML TextField wingsfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField xmru=new TextField();
    @FXML TextField xmrfiat=new TextField();
    //End of EOS controls

    //EOS controls
    @FXML TextField zecu=new TextField();
    @FXML TextField zecfiat=new TextField();
    //End of EOS controls

    public static String callURL(String myURL) {
        //System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }

        return sb.toString();
    }
    @FXML
    private void initialize() throws org.json.simple.parser.ParseException {

        rb1.setToggleGroup(grp);
        rb2.setToggleGroup(grp);
        rb3.setToggleGroup(grp);
        rb4.setToggleGroup(grp);
        rb2.setSelected(true);



        ScrollPane sideBarScroller = new ScrollPane(cont);
        sideBarScroller.setFitToHeight(true);


        //sp.setContent(cont);
       // sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        readBTC();
        readLTC();
        readANT();
        readBAT();
        readBNT();
        readBLK();
        readBTS();
        readCVC();
        readDCR();
        readDGD();
        readEDG();
        readEOS();
        readFUN();
        readGAM();
        readGNO();
        readGNT();
        readGUP();
        readICN();
        readKMD();
        readLBC();
        readMLN();
        readMON();
        readNMC();
        readNMR();
        readOMG();
        readREP();
        readRDD();
        readRLC();
        readSC();
        readSNT();
        readSTART();
        readVOX();
        readVTC();
        readVRC();
        readWAVES();
        readWINGS();
        readXMR();
        readZEC();



    }
    public void updateCOINS(){

        readBTC();
        readLTC();
        readANT();
        readBAT();
        readBNT();
        readBLK();
        readBTS();
        readCVC();
        readDCR();
        readDGD();
        readEDG();
        readEOS();
        readFUN();
        readGAM();
        readGNO();
        readGNT();
        readGUP();
        readICN();
        readKMD();
        readLBC();
        readMLN();
        readMON();
        readNMC();
        readNMR();
        readOMG();
        readREP();
        readRDD();
        readRLC();
        readSC();
        readSNT();
        readSTART();
        readVOX();
        readVTC();
        readVRC();
        readWAVES();
        readWINGS();
        readXMR();
        readZEC();

    }



    public void readBTC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(btc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        btcu.clear();
        btcu.setText("1");
        btcfiat.clear();
        btcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        btcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(btcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        btcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        btcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readLTC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(ltc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        ltcu.setText("1");
        ltcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        ltcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(ltcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        ltcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        ltcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readANT() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(ant_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        antu.setText("1");
        antfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        antfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(antfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        antu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        antfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readBAT() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(bat_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        batu.setText("1");
       batfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        batfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(batfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        batu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        batfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readBNT() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(bnt_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        bntu.setText("1");
        bntfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        bntfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(bntfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        bntu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        bntfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readBLK() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(blk_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        blku.setText("1");
        blkfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        blkfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(blkfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        blku.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        blkfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readBTS() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(bts_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        btsu.setText("1");
        btsfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        btsfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(btsfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        btsu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        btsfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readCVC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(cvc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        cvcu.setText("1");
        cvcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        cvcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(cvcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        cvcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        cvcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readDCR() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(dcr_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        dcru.setText("1");
        dcrfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        dcrfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(dcrfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        dcru.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        dcrfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readDGD() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(dgd_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        dgdu.setText("1");
        dgdfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        dgdfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(dgdfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        dgdu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        dgdfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readEDG() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(edg_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        edgu.setText("1");
        edgfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        edgfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(edgfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        edgu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        edgfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readEOS() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(eos_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        eosu.setText("1");
        eosfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        eosfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(eosfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        eosu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        eosfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readFUN() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(fun_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        funu.setText("1");
        funfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        funfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(funfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        funu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        funfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readGAM() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(gam_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        gamu.setText("1");
        gamfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        gamfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(gamfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        gamu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        gamfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readGNO() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(gno_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        gnou.setText("1");
        gnofiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        gnofiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(gnofiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        gnou.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        gnofiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readGNT() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(gnt_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        gntu.setText("1");
        gntfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        gntfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(gntfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        gntu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        gntfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readGUP() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(gup_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        gupu.setText("1");
        gupfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        gupfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(gupfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        gupu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        gupfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readICN() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(icn_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        icnu.setText("1");
        icnfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        icnfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(icnfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        icnu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        icnfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readKMD() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(kmd_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        kmdu.setText("1");
        kmdfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        kmdfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(kmdfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        kmdu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        kmdfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readLBC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(lbc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        lbcu.setText("1");
        lbcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        lbcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(lbcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        lbcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        lbcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readMLN() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(mln_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        mlnu.setText("1");
        mlnfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        mlnfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(mlnfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        mlnu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        mlnfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readMON() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(mon_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        monu.setText("1");
        monfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        monfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(monfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        monu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        monfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readNMC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(nmc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        nmcu.setText("1");
        nmcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        nmcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(nmcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        nmcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        nmcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readNMR() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(nmr_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        nmru.setText("1");
        nmrfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        nmrfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(nmrfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        nmru.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        nmrfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readOMG() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(omg_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        omgu.setText("1");
        omgfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        omgfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(omgfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        omgu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        omgfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readREP() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(rep_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        repu.setText("1");
        repfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        repfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(repfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        repu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        repfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readRDD() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(rdd_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        rddu.setText("1");
        rddfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        rddfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(rddfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        rddu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        rddfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readRLC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(rlc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        rlcu.setText("1");
        rlcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        rlcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(rlcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        rlcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        rlcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readSC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(sc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        scu.setText("1");
        scfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        scfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(scfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        scu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        scfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readSNT() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(snt_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        sntu.setText("1");
        sntfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        sntfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(sntfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        sntu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        sntfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readSTART() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(start_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        startu.setText("1");
        startfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        startfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(startfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        startu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        startfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readVOX() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(vox_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        voxu.setText("1");
        voxfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        voxfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(voxfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        voxu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        voxfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readVTC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(vtc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        vtcu.setText("1");
        vtcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        vtcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(vtcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        vtcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        vtcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readVRC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(vrc_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        vrcu.setText("1");
        vrcfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        vrcfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(vrcfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        vrcu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        vrcfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readWAVES() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(waves_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        wavesu.setText("1");
        wavesfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        wavesfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(wavesfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        wavesu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        wavesfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readWINGS() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(wings_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        wingsu.setText("1");
       wingsfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        wingsfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(wingsfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        wingsu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        wingsfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readXMR() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(xmr_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        xmru.setText("1");
        xmrfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        xmrfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(xmrfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        xmru.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        xmrfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }
    public void readZEC() throws  NumberFormatException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(callURL(zec_url));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        zecu.setText("1");
        zecfiat.setText( json.get("USD").toString());

        JSONObject finalJson = json;

        zecfiat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")){
                    System.out.println("FACK");
                    return;
                }
                else  {
                    try {
                        double d1=convStoD(zecfiat.getText());
                        double d2=convStoD(finalJson.get("USD").toString());
                        NumberFormat formatter = new DecimalFormat("#0.00000000");
                        zecu.setText(formatter.format(d1/d2).toString());
                    }
                    catch ( NumberFormatException e){
                        JOptionPane.showMessageDialog(null,
                                "Eggs are not supposed to be green.",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                        zecfiat.clear();
                    }
                    catch ( IllegalArgumentException e){

                    }

                }

            }
        });

    }

    public double convStoD(String s){
        double d = Double.parseDouble(s);
        return d;
    }
}
