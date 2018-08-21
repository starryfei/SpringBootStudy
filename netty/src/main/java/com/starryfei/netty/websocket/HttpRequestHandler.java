package com.starryfei.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
/**
 * 
 * @ClassName  HttpRequestHandler   
 * @Description 处理 HTTP 请求
 * @author yafei.qin 
 * @date 2018年8月20日 下午2:42:09   
 *
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private static final File INDEX;
    static {
        URL location = HttpRequestHandler.class.getProtectionDomain()
                .getCodeSource().getLocation();
        try {
            String path = location.toURI() + "client.html";
            System.out.println(location.toURI()+" "+path);
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Unable to locate client.html", e);
        }
    }
    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request)
            throws Exception {
        //http请求/websocket请求
        if (wsUri.equalsIgnoreCase(request.uri())) {
            //请求是 WebSocket 升级，递增引用计数器（保留）并且将它传递给在 ChannelPipeline 中的下个 ChannelInboundHandler
            //如果该 HTTP 请求被发送到URI “/ws”，则调用 FullHttpRequest 上的 retain()，并通过调用 fireChannelRead(msg) 转发到下一个
            //ChannelInboundHandler.retain() 的调用是必要的，因为 channelRead() 完成后，它会调用 FullHttpRequest 上的 release() 来释放其资源
            ctx.fireChannelRead(request.retain());
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (keepAlive) {
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH,file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);
            //判断 SslHandler 是否在 ChannelPipeline 来决定是使用 DefaultFileRegion 还是 ChunkedNioFile
            //如果传输过程既没有要求加密也没有要求压缩，那么把 index.html 的内容存储在一个 DefaultFileRegion 里就可以达到最好的效率。这将利用零拷贝来执行传输。
            //出于这个原因，我们要检查 ChannelPipeline 中是否有一个 SslHandler。如果是的话，我们就使用 ChunkedNioFile。
            if (ctx.pipeline().get(SslHandler.class) == null) {     
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            //标记响应的结束，并终止它
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);           //8
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);        
            }
            
            file.close();
        }
    }
    /**
     * 
     * @Description 处理 100 continue
     * @author yafei.qin
     * @param ctx  void
     */
    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
        System.out.println(response.headers());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
