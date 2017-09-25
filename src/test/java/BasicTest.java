import org.junit.Test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BasicTest {

    @Test
    public void testIp() {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println(Inet4Address.getLocalHost().getHostAddress());
            System.out.println(Inet6Address.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
