package com.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * Created by potoyang on 2017/8/22.
 */

public class PubSync {
    @org.junit.Test
    public void doTest() {
        try {
            MqttClient client = new MqttClient("tcp://192.168.1.100:1883", "java_client", null);
            MqttTopic topic = client.getTopic("topic");
            MqttMessage message = new MqttMessage("Hello world".getBytes());
            message.setQos(1);
            client.connect();
            MqttDeliveryToken token = topic.publish(message);
            while (!token.isComplete()) {
                token.waitForCompletion(1000);
            }
            client.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        return;
    }
}
