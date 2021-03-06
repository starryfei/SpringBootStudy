package com.starryfei.netty.chat.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * 
 * @ClassName  ChatServerInitializer   
 * @Description ChatServerInitializer 用来增加多个的处理类到 ChannelPipeline 上
 * 包括编码、解码、SimpleChatServerHandler 等。  
 * @author yafei.qin 
 *
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel>{
    private Logger logger = LoggerFactory.getLogger(ChatClientInitializer.class);
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new ChatClientHandler());
 
        logger.info(("Client:"+ch.remoteAddress() +"连接上"));
        
    }

}
