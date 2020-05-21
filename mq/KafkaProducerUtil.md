## kafka生产者工具类

```java
package com.mq;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@Component
public class KafkaProduceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProduceUtil.class);

    //创建一个容器
    private static Map<String, KafkaProducer> producers;

    private static String kafkaAddr;

    //次方法可以在注入bean时获取kafka地址
//    @Value(@Kafka.addr)
    public  void setKafkaAddr(String kafkaAddr) {
        KafkaProduceUtil.kafkaAddr = kafkaAddr;
        produce("kafkaInit");
    }

    private  void produce(String brokerKey) {
        if (producers.get(brokerKey)==null || producers.get(brokerKey)instanceof KafkaProducer){
            //初始化producer ,并保存到map中
            Properties properties = new Properties();
            properties.put("bootstrap.servers",kafkaAddr);
            properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
            //将生产者放入map中
            producers.put(brokerKey,producer);
        }
    }

    public static void  produce(String brokerKey,String topic,String message,Integer ttl){
        if (producers.get(brokerKey)==null || producers.get(brokerKey)instanceof KafkaProducer){
            //初始化producer ,并保存到map中
            Properties properties = new Properties();
            properties.put("bootstrap.servers",kafkaAddr);
            properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
            //将生产者放入map中
            producers.put(brokerKey,producer);
        }
        //推送kafka失败尝试次数指定
        Map<String,Integer> prop = new HashMap<>();
        prop.put("ttl",ttl);
        sengMessage(producers.get(brokerKey),topic,message,prop);
        if (prop.get("ttl")==0){
            throw new KafkaException("消息推送到kafka服务器失败");
        }
    }

    private static void sengMessage(final KafkaProducer kafkaProducer, final String topic, final String message, final Map<String, Integer> prop) {
        if (prop.get("ttl")==0){
            return;
        }
        LOGGER.info("sengMessage ttl次数"+prop.get("ttl"));
        LOGGER.info("sengMessage topic"+topic+";meaage"+message);

        try {
            kafkaProducer.send(new ProducerRecord<>(topic, message), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e!=null){
                        prop.put("ttl",prop.get("ttl")-1);
                        LOGGER.info("发送失败--再次尝试发送");
                        LOGGER.info(e.getMessage());
                        sengMessage(kafkaProducer,topic,message,prop);
                    }else {
                        LOGGER.info("发送成功");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


```
