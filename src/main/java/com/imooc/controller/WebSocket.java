package com.imooc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

// 使用websocket有两步，
// 一、配置全局config 二、下面代码
@Component
@Slf4j
@ServerEndpoint("/websocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArrayList<WebSocket> webSockets = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;

        webSockets.add(this);

        log.info("【有新连接】，连接总数为：{}", webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);

        log.info("【断开连接】，连接总数为：{}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String msg) {
        log.info("【收到新消息】：{}", msg);
    }

    public void sendMessage(String msg) {

        webSockets.forEach(item -> {
            log.info("【发送消息】：{}", msg);

            try {
                item.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
