package ru.ea.l3.controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import ru.ea.l3.controllers.xslSettings.Students;
import ru.ea.l3.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.util.List;

@Controller
@RequestMapping("/xslt")
public class XsltController {

    private StudentResource studentResource;

    @Autowired
    public XsltController(StudentResource studentResource) {
        this.studentResource = studentResource;
    }

    private Source createS(){
        String URL =
                "http://localhost:8080/student";
        RestTemplate restTemplate = new RestTemplate();
        Source response = restTemplate.getForObject(URL,Source.class);

        return response;

    }
    private Source createXsltSource(String string) throws Exception {
        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><a><b></b><c></c></a>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DOMSource(document);
    }

    @RequestMapping(value = "/students")
    public ModelAndView findOwners() throws Exception {
        final ModelAndView modelAndView = new ModelAndView("lists");
        //Students st = new Students(studentResource.getAllStudents());
        //Source source = createXsltSource(st);
        Source response = createS();

        modelAndView.addObject("xmlSource", response);
        return modelAndView;
    }


}