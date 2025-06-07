package main.java;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;

public class ModbusTcpExample {
    public static void main(String[] args) throws Exception {
        ModbusFactory factory = new ModbusFactory();
        IpParameters params = new IpParameters();
        params.setHost("192.168.1.1");
        params.setPort(502);

        ModbusMaster master = factory.createTcpMaster(params, false);
        master.init();

        // 读取寄存器（地址 0，数量 10）
//        short[] values = master.readHoldingRegisters(1, 0, 10);
//        for (short val : values) {
//            System.out.println("寄存器值: " + val);
//        }

        master.destroy();
    }
}
