package qa.guru.rococo.api.interceptor;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import qa.guru.rococo.api.context.CookieContext;

public class AddCookieInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse response, HttpContext context) {
        CookieContext cookies = CookieContext.getInstance();
        cookies.setJsessionid(getSessionId(response));
        cookies.setXsrf(getXsrfToken(response));
    }

    private static String getSessionId(HttpResponse response) {
        if(response.getHeaders("Set-Cookie").length == 0) {
            return null;
        }
        Header header =  response.getHeaders("Set-Cookie")[0];
        String[] values = header.getValue().split(";");
        for (String value : values) {
            if(value.contains("JSESSIONID")){
                return value.substring(value.indexOf("=") + 1);
            }
        }

        return null;
    }

    private static String getXsrfToken(HttpResponse response) {
        if(response.getHeaders("Set-Cookie").length == 0) {
            return null;
        }
        Header header =  response.getHeaders("Set-Cookie")[0];
        String[] values = header.getValue().split(";");
        for (String value : values) {
            if(value.contains("XSRF-TOKEN")){
                return value.substring(value.indexOf("=") + 1);
            }
        }

        return null;
    }
}
