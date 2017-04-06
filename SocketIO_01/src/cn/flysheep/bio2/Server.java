package cn.flysheep.bio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by FlySheep on 17/4/6.
 */
public class Server {
    final static int PORT = 8765;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(" server start...");
            // 进行阻塞
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
            while (true) {
                socket = serverSocket.accept();
                executorPool.execute(new ServerHandler(socket));
            }
            // 新建一个线程执行客户端的任务
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }
    }
}
