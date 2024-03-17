import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FreeMarker 学习测试
 */
public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl", "UTF-8");

        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2024);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://github.com/Mr-JoKerY/YunAPI");
        menuItem1.put("label", "Yun API");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://github.com/Mr-JoKerY/YunBI");
        menuItem2.put("label", "Yun 智能BI平台");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);

        // 生成
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("myweb.html")), StandardCharsets.UTF_8));
        template.process(dataModel, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }
}