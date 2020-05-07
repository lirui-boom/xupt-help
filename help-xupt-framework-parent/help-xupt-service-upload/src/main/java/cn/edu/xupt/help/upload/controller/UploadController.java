package cn.edu.xupt.help.upload.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.utils.QiniuCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"文件上传接口"})
@RestController
public class UploadController {

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {

        String url = QiniuCloudUtil.uploadImg(file);

        if (StringUtils.isEmpty(url)) {
            return ResponseResult.build(500, "文件上传失败！");
        }

        return ResponseResult.ok(url);
    }
}
