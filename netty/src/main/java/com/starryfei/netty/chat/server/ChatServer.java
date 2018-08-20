package com.starryfei.netty.chat.server;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {
 private static int PORT = 9112;
    
    @SuppressWarnings("unused")
    private void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 用来接收进来的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //处理已经被接收的连接
        ServerBootstrap boot = new ServerBootstrap();
        try{
            boot.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
            .childHandler(new ChatServerInitializer())
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);
            
//            option() 是提供给NioServerSocketChannel 用来接收进来的连接。
//            childOption() 是提供给由父管道 ServerChannel 接收到的连接。
            ChannelFuture f = boot.bind(PORT).sync();
            System.out.println("服务端启动成功...");
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        try {
            new ChatServer().run();
            Callable<String> cal = new Callable<String>() {
                public String call() throws Exception {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
