package cn.bounter.job.schedule;

import cn.bounter.common.util.DateUtil;
import cn.bounter.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileJob {

    private static final Logger logger = LoggerFactory.getLogger(FileJob.class);

    /**
     * 每天凌晨2点执行一次
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void clean() throws ParseException {
        logger.info("开始删除文件...");
        long startTime = System.currentTimeMillis();

        //删除上传文件
        File deleteFile = new File("C:\\jswzdata\\upload");
        File[] deleteFiles = deleteFile.listFiles();
        for (File uploadFile : deleteFiles) {
            Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(uploadFile.getName());
            //删除15天前的文件
            if (new Date().compareTo(DateUtil.getDateIn(DateUtil.DAY, 15, createDate)) > 0) {
                FileUtil.deleteFile(uploadFile);
            }
        }

        //删除转换文件
        deleteFile = new File("C:\\jswzdata\\convert");
        deleteFiles = deleteFile.listFiles();
        for (File convertFile : deleteFiles) {
            Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(convertFile.getName());
            //删除15天前的文件
            if (new Date().compareTo(DateUtil.getDateIn(DateUtil.DAY, 15, createDate)) > 0) {
                FileUtil.deleteFile(convertFile);
            }
        }

        logger.info("删除文件结束,总耗时：[{}]毫秒", System.currentTimeMillis() - startTime);
    }

}
