
package it.sapienza.softeng.exam;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "InterfaceImplService", targetNamespace = "http://exam.softeng.sapienza.it/", wsdlLocation = "http://192.168.50.83:8080/Interface?wsdl")
public class InterfaceImplService
    extends Service
{

    private final static URL INTERFACEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException INTERFACEIMPLSERVICE_EXCEPTION;
    private final static QName INTERFACEIMPLSERVICE_QNAME = new QName("http://exam.softeng.sapienza.it/", "InterfaceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.50.83:8080/Interface?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INTERFACEIMPLSERVICE_WSDL_LOCATION = url;
        INTERFACEIMPLSERVICE_EXCEPTION = e;
    }

    public InterfaceImplService() {
        super(__getWsdlLocation(), INTERFACEIMPLSERVICE_QNAME);
    }

    public InterfaceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INTERFACEIMPLSERVICE_QNAME, features);
    }

    public InterfaceImplService(URL wsdlLocation) {
        super(wsdlLocation, INTERFACEIMPLSERVICE_QNAME);
    }

    public InterfaceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INTERFACEIMPLSERVICE_QNAME, features);
    }

    public InterfaceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InterfaceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Interface
     */
    @WebEndpoint(name = "InterfaceImplPort")
    public Interface getInterfaceImplPort() {
        return super.getPort(new QName("http://exam.softeng.sapienza.it/", "InterfaceImplPort"), Interface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Interface
     */
    @WebEndpoint(name = "InterfaceImplPort")
    public Interface getInterfaceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://exam.softeng.sapienza.it/", "InterfaceImplPort"), Interface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INTERFACEIMPLSERVICE_EXCEPTION!= null) {
            throw INTERFACEIMPLSERVICE_EXCEPTION;
        }
        return INTERFACEIMPLSERVICE_WSDL_LOCATION;
    }

}
