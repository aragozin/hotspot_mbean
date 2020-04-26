package info.ragozin.poc;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.junit.Test;

import sun.management.HotspotInternal;

@SuppressWarnings("restriction")
public class HotspotRuntimeAccessTest {

    @Test
    public void testManagementFactoryHelper() {
        sun.management.HotspotRuntimeMBean runtime = sun.management.ManagementFactoryHelper.getHotspotRuntimeMBean();
        System.out.println("TotalSafePointTime: " + runtime.getTotalSafepointTime());
    }

    @Test
    public void testMBean() throws IntrospectionException, InstanceNotFoundException, MalformedObjectNameException, ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
        HotspotInternal hi = new HotspotInternal();
        ManagementFactory.getPlatformMBeanServer().registerMBean(hi, null);

        String mname = "sun.management:type=HotspotThreading";
        MBeanInfo info = ManagementFactory.getPlatformMBeanServer().getMBeanInfo(new ObjectName(mname));
        System.out.println(mname + ": " + info);
    }
}
