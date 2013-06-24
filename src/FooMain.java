import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.XMLConstants;

import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Validator;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;



public class FooMain {
    public static void main(String args[]) throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema SCHEMA = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema( new StreamSource(new File("A.xsd")));
        Validator validator = SCHEMA.newValidator();
        SAXSource source = new SAXSource(new InputSource(new ByteArrayInputStream(
                              StandardCharsets.UTF_8.decode(ByteBuffer.wrap(Files.readAllBytes(Paths.get("a.xml")))).toString().getBytes())));

        try {
            validator.validate(source);
            System.out.println("validates");
        }  catch (SAXException e) {
            System.out.println("doesn't validate");
        }
    }
}