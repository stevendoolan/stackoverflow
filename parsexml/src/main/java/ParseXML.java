
import java.io.IOException;
import java.io.StringReader;
import java.util.AbstractList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * An answer to question:
 * 
 * http://stackoverflow.com/questions/20673520/regular-expression-java-xml-pig/20674973#20674973
 * 
 * Does not quite answer the question 100%, but demonstrates a way to parse XML "properly" using XPath.
 * 
 * @author Steven Doolan
 *
 */
public class ParseXML {

    public static void main(String... args) throws Exception {
        String input = "<Content><Data Name=\"Buffer\">XXXYYYZZZ</Data><Data Name=\"Buffer\">111222333</Data></Content>";
        String xpathExpression = "//Data[@Name='Buffer']";
        NodeList result = parseXML(input, xpathExpression);
        for (Node node : new NodeListWrapper(result)) {
            System.out.println(node.getFirstChild().getTextContent());
        }
    }

    private static NodeList parseXML(String input, String xpathExpression) throws Exception {
        Document document = createDocument(input);
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile(xpathExpression);
        return (NodeList) expression.evaluate(document, XPathConstants.NODESET);
    }

    private static Document createDocument(String input) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        return documentBuilder.parse(new InputSource(new StringReader(input)));
    }

}

class NodeListWrapper extends AbstractList<Node> {
    private final NodeList nodeList;

    public NodeListWrapper(NodeList nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public Node get(int n) {
        return nodeList.item(n);
    }

    @Override
    public int size() {
        return nodeList.getLength();
    }
}