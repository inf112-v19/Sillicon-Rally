package inf112.skeleton.app;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//reading the data from the .tmx map files and returns the numeric value of a tile
public class TileReader {
    //TODO find a way to import path of chosen map
    File mapFile = new File("path of map");
    String data="";
    String[] mapdata;
    BufferedReader br = new BufferedReader(new FileReader(mapFile));
    int teller=0;

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(mapFile);

    //TODO lese inn "dataen" fra mappet og sette dem inn i en 2d array
    //dataen er i et xml format under taget data, så må vi ta å lese det inn i en 2d array linje for linje?



    public TileReader() throws ParserConfigurationException, IOException, SAXException {
    }
}
