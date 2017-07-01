package com.gooda.push;

import android.content.Context;

import com.gooda.push.message.PushMsgUserData;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

/**
 * Created by 任伟伟
 * Datetime: 2017/2/15-17:15
 * Email: renweiwei@ufashion.com
 */

public class PushAgent{
    public static String SHOPPING_GUIDE_ = "SHOPPING_GUIDE_";
    public static String TOPIC_USER_DATA = "SHOPPING_GUIDE_USER_DATA_";
    public static String TOPIC_CLOTHES_SIZE_DATA = "SHOPPING_GUIDE_CLOTHES_SIZE_DATA_";
    private Context mContext;
    MqttAndroidClient mqttAndroidClient;
//    final String serverUri = "tcp://192.168.0.10:1883";//测试
    final String serverUri = "tcp://123.56.179.192:1883";//线上
    private static String clientId = "Android_"+UUID.randomUUID().toString();//设备号
    private String[] topics;
    private int[] qos;
    private IMqttMessageListener[] mqttMessageListeners;

    private PushAgent(Context context){
        mContext = context;
    }
    private static volatile PushAgent instance = null;
    public static synchronized PushAgent getInstance(Context context){
        if(instance==null){
            synchronized (PushAgent.class){
                if(instance==null){
                    instance = new PushAgent(context);
                    clientId = DeviceUtil.genDeviceId(context);
                }
            }
        }
        return instance;
    }

    public void register(RegisterCallback registerCallback){
        this.registerCallback = registerCallback;
    }


    private void initMqttClient() {
        mqttAndroidClient = new MqttAndroidClient(mContext.getApplicationContext(), serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect) {//重新连接之后需要重新订阅主题
                    //addToHistory("Reconnected to : " + serverURI);
                    // Because Clean Session is true, we need to re-subscribe
                    toSubscribe();
                } else {
                    //addToHistory("Connected to: " + serverURI);
                }
            }
            @Override
            public void connectionLost(Throwable cause) {
                //addToHistory("The Connection was lost.");
                System.out.println("Message: ");
            }
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //addToHistory("Incoming message: " + new String(message.getPayload()));
                System.out.print(topic);
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("Message: ");
            }
        });

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);//设置为 false，那么客户机创建的任何预订都会被添加至客户机在连接之前就已存在的所有预订。当客户机断开连接时，所有预订仍保持活动状态
        try {
            //addToHistory("Connecting to " + serverUri);
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    toSubscribe();//如果这个地方注册topic则走的是mqttAndroidClient.subscribe(subscriptionTopic, 0, new IMqttMessageListener() {
                    if(registerCallback!=null)registerCallback.onSuccess(asyncActionToken.getClient().getClientId());
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //addToHistory("Failed to connect to: " + serverUri);
                    if(registerCallback!=null)registerCallback.onFailure(asyncActionToken.getClient().getClientId(),exception.getMessage());
                }
            });


        } catch (MqttException ex){
            ex.printStackTrace();
        }
    }

    private void toSubscribe() {
        try {
            mqttAndroidClient.subscribe(topics, qos,mqttMessageListeners);

        } catch (MqttException ex){
            System.err.println("Exception whilst subscribing");
            ex.printStackTrace();
        }
    }

    //取消消息订阅
    public void unSubscribe(String topic){
        try {
            mqttAndroidClient.unsubscribe(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String[] topics){
        this.topics = topics;
        this.qos = new int[topics.length];
        this.mqttMessageListeners = new IMqttMessageListener[topics.length];
        for(int i=0;i<topics.length;i++){
            qos[i] = 0;
            this.mqttMessageListeners[i] = new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
//                    Intent intent = new Intent(mContext,MessageReceiver.class);
//                    intent.setAction(MessageReceiver.GOODA_PUSH);
//                    intent.putExtra("topic",topic);
//                    intent.putExtra("message",new String(message.getPayload()));
//                    mContext.sendBroadcast(intent);
                    PushMsgUserData msg = new PushMsgUserData();
                    msg.setMsg(new String(message.getPayload()));
                    msg.setTopic(topic);
                    EventBus.getDefault().post(msg);
                }
            };
        }
        initMqttClient();
    }

    public interface RegisterCallback{
        public void onSuccess(String deviceToken);
        public void onFailure(String s, String s1);
    }
    private RegisterCallback registerCallback;

}
