package qa.guru.rococo.api.interceptor;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import qa.guru.rococo.api.context.LocalStorageContext;

public class AddCodeInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest request, HttpContext context) {
        LocalStorageContext localStorageContext = LocalStorageContext.getInstance();
        String uri = request.getRequestLine().getUri();
        if(uri.startsWith("/authorized")) {
            localStorageContext.setCode(uri.substring(uri.indexOf("=") + 1));
            System.out.println();
        }
    }
}
