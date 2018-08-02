package com.starryfei.netty.chat.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName  ChatServerHandler   
 * @Description TODO(这里用一句话描述这个类的作用)   
 * @author yafei.qin 
 *
 */
public final class ChatServerHandler extends SimpleChannelInboundHandler<String>{
    private static final Logger logger = LoggerFactory.getLogger(ChatServerHandler.class.getName());

//   ChannelGroup可管理服务器端所有的连接的Channel，然后对所有的连接Channel广播消息
    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        //服务端读到客户端写入信息时，将信息转发给其他客户端的 Channel
        Channel comming = ctx.channel();
        for (Channel channel : group) {
            if (comming == channel) {
                ctx.writeAndFlush("[you]"+msg+"\n");
            } else {
                ctx.writeAndFlush("["+channel.remoteAddress()+"]" +msg+"\n");
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // TODO Auto-generated method stub
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"掉线");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel newTalker = ctx.channel();
        group.writeAndFlush("Message:  "+newTalker.remoteAddress() +" 加入聊天室");
        group.add(newTalker);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel leaveTalker = ctx.channel();
        logger.info(leaveTalker.remoteAddress()+" leave");
        group.writeAndFlush("Message:  "+leaveTalker.remoteAddress() +" 加入聊天室");
        group.remove(leaveTalker);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
     // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }


}
