package main.java;


import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

import java.util.concurrent.CompletableFuture;

public class OpcUaExample {
    public static void main(String[] args) throws Exception {
        String endpointUrl = "opc.tcp://localhost:4840";
        OpcUaClient client = (OpcUaClient) OpcUaClient.create(endpointUrl).connect().get();
        TimestampsToReturn timestampsToReturn = TimestampsToReturn.Both;
        // 读取节点（ns=2;s=Device1.Temperature）
        NodeId nodeId = NodeId.parse("ns=2;s=Device1.Temperature");
        Double value = (Double) client.readValue(0, timestampsToReturn, nodeId).get().getValue().getValue();
        System.out.println("温度值: " + value);

        client.disconnect().get();
    }
}