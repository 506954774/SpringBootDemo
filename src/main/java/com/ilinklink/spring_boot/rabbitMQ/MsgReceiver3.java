package com.ilinklink.spring_boot.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import static com.ilinklink.spring_boot.ServerConstants.LOGGER_PREFIX;

/**
 * MsgReceiver
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/1/11  15:50
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver3 {

    @RabbitHandler
    public void process(String content) {
        log .info(LOGGER_PREFIX+"rabbitMQ,"+"MsgReceiver3,接收处理队列A当中的消息： " + content);
    }

}
