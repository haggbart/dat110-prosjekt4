package no.hvl.dat110.aciotdevice.client;

public class RestClient {

    private static String logpath = "/accessdevice/log";
    private static String codepath = "/accessdevice/code";

    public RestClient() {
        // TODO Auto-generated constructor stub
    }

    public void doPostAccessEntry(String message) {

        // TODO: implement a HTTP POST on the service to post the message

    }

    public AccessCode doGetAccessCode() {

        AccessCode code = null;

        // TODO: implement a HTTP GET on the service to get current access code

        return code;
    }
}
