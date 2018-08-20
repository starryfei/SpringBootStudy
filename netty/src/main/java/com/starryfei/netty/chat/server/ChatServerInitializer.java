package com.starryfei.netty.chat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
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
public class ChatServerInitializer extends ChannelInitializer<SocketChannel>{
    private Logger logger = LoggerFactory.getLogger(ChatServerInitializer.class);
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //解码器解决半包读写问题.LinBasedFrameDecoder
        //linebaseframedecoder是以换行符为结束标记的解码器
        //StringDecoder解码器就是将对象转成字符串
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new ChatServerHandler());
 
        logger.info(("Client:"+ch.remoteAddress() +"连接上"));
        
    }

}
