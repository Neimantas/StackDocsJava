package Configuration;

import java.nio.file.Paths;
import java.util.Optional;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import Services.Impl.IndexServlet;
import Services.Impl.ReadServlet;
import eu.lestard.easydi.EasyDI;

public class Main {
    
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    
    public static void main(String[] args) throws Exception {
//        String contextPath = "/";
//        String appBase = ".";
//        Tomcat tomcat = new Tomcat();     
//        tomcat.setPort(Integer.valueOf(port.orElse("8080") ));
//        tomcat.getHost().setAppBase(appBase);
//        tomcat.addWebapp(contextPath, appBase);
//       
////        Context context = tomcat.addContext("", Paths.get(".").toAbsolutePath().toString());
//
////        Tomcat.addServlet(context, "main", new IndexServlet());
////        context.addServletMapping("/main", "main");
//        
//        tomcat.start();
//        tomcat.getServer().await();
    	String contextPath = "/" ;
        String appBase = ".";
        Tomcat tomcat = new Tomcat();   
        tomcat.setPort(Integer.valueOf(PORT.orElse("8080") ));
        tomcat.setHostname(HOSTNAME.orElse("localhost"));
        tomcat.getHost().setAppBase(appBase);
        
        Context context = tomcat.addWebapp(contextPath, appBase);
//        context.addContainerListener(new StartupContainer());
        EasyDI di = new EasyDI();
        Tomcat.addServlet(context, "main", new IndexServlet(di));
        Tomcat.addServlet(context, "read", new ReadServlet(di));
        
        context.addServletMapping("/main", "main");
        context.addServletMapping("/read", "read");
        
        tomcat.start();
        tomcat.getServer().await();
    }
}