package cn.leancloud.leo.utils;

import cn.leancloud.leo.model.Message;
import cn.leancloud.leo.model.TextMsg;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.thoughtworks.xstream.XStream;
import org.omg.CORBA.MARSHAL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class MessageXmlUtil {


//    /**
//     * 将对象直接转换成String类型的 XML输出
//     *
//     * @param obj
//     * @return
//     */
//    public static String convertToXml(Object obj) {
//
//    }
//
//    public static <T> T convertToObject(String xml, Class<T> clazz) {
//        StringReader sr = new StringReader(xml);
//        try {
//            JAXBContext context = JAXBContext.newInstance(clazz);
//
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//
//            return (T) unmarshaller.unmarshal(sr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static void main(String[] args) {
//        TextMsg msg = new TextMsg();
//        msg.setFromUserName("yy");
//        msg.setToUserName("yang");
//        msg.setContent("nothing");
//        msg.setCreateTime(111123l);
//
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
//                "<xml>\n" +
//                "    <ToUserName>yang</ToUserName>\n" +
//                "    <FromUserName>yy</FromUserName>\n" +
//                "    <CreateTime>12356677</CreateTime>\n" +
//                "    <Content>nothing</Content>\n" +
//                "    <MsgType>text</MsgType>   "+
//                "</xml>\n";
//
//        XStream xStream = new XStream();
//        xStream.alias("xml", TextMsg.class);
//        xStream.aliasField("ToUserName", TextMsg.class,"toUserName");
//        xStream.aliasField("FromUserName", TextMsg.class, "fromUserName");
//        xStream.aliasField("CreateTime", TextMsg.class, "createTime");
//        xStream.aliasField("Content", TextMsg.class, "content");
//        xStream.aliasField("MsgType", TextMsg.class, "msgType");
//        Object returnObj = xStream.fromXML(xml);
//        System.out.println(returnObj);

//    }
}