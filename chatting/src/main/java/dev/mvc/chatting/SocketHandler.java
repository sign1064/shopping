package dev.mvc.chatting;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
    
    HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //������ ������ ��Ƶ� ��
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // �޽��� �߼�, key�� ���� ���·� �ο���.
        String msg = message.getPayload();
        for(String key : sessionMap.keySet()) {
            System.out.println("�޽��� �߼� key: " + key);
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // ���� ����
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session); // id�� ���� ���·� �ο��Ǹ� �޽��� ���۽� key�� �̿��
        System.out.println("���� ���� session.getId(): " + session.getId());
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // ���� ����
        System.out.println("���� ���� session.getId(): " + session.getId()); // id�� ���� ���·� �ο��Ǹ� �޽��� ���۽� key�� �̿��
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
