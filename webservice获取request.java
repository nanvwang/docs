import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.jaxws.context.WebServiceContextImpl;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

        MessageContext ctx = new WebServiceContextImpl().getMessageContext();
        HttpServletRequest req = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
        String ctxpath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();