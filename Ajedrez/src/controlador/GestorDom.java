package controlador;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.Partida;

public class GestorDom {
private Document doc;
	

	public GestorDom() {
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factoria.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void crearDocumentos() {
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factoria.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element partidas = doc.createElement("partidas");
		doc.appendChild(partidas);
		
	}

	public void crearXml(String fichero) {
		TransformerFactory factoria =TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = factoria.newTransformer(new StreamSource(new File("f.xslt")));
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File(fichero));

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarPartida(Partida partida, int numPartida) {
		Element raiz = doc.getDocumentElement();
		
		Element partidaElement = doc.createElement("partida");
		partidaElement.setAttribute("numero", String.valueOf(numPartida));
		
		Element jugadoresElement = doc.createElement("jugadores");
		jugadoresElement.setTextContent(partida.getJugadores());
		partidaElement.appendChild(jugadoresElement);
		
		Element movimientosElement;
		for (int i = 0; i < partida.getMovimientos().size(); i++) {
			movimientosElement = doc.createElement("movimiento");
			movimientosElement.setTextContent(partida.getMovimientos().get(i));
			movimientosElement.setAttribute("n", String.valueOf(i+1));
			partidaElement.appendChild(movimientosElement);
		}
		
		raiz.appendChild(partidaElement);
	}
}
