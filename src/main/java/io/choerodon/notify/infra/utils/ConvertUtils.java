package io.choerodon.notify.infra.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.notify.NotifyType;
import io.choerodon.notify.api.dto.BusinessTypeDTO;
import io.choerodon.notify.infra.dto.Config;
import io.choerodon.notify.infra.dto.SendSettingDTO;
import io.choerodon.notify.infra.dto.Template;
import io.choerodon.notify.infra.enums.SendingTypeEnum;
import io.choerodon.swagger.notify.NotifyTemplateScanData;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {

    private static final String EMAIL_SSL = "mail.smtp.ssl.enable";

    private static final String EMAIL_SEND_NAME = "mail.send.name";

    private ConvertUtils() {
    }

    public static Config mailProperties2Config(MailProperties mailProperties) {
        Config config = new Config();
        config.setEmailSendName(mailProperties.getUsername());
        config.setEmailAccount(mailProperties.getUsername());
        config.setEmailHost(mailProperties.getHost());
        config.setEmailPassword(mailProperties.getPassword());
        config.setEmailPort(mailProperties.getPort());
        config.setEmailProtocol(mailProperties.getProtocol());
        config.setEmailSsl(false);
        String ssl = mailProperties.getProperties().get(EMAIL_SSL);
        if (ssl != null) {
            config.setEmailSsl(Boolean.parseBoolean(ssl));
        }
        config.setEmailSendName(mailProperties.getProperties().get(EMAIL_SEND_NAME));
        return config;
    }

    public static Template convertNotifyTemplate(final NotifyTemplateScanData scanData) {
        Template template = new Template();
        template.setIsPredefined(true);
        template.setSendSettingCode(scanData.getBusinessType());
        if (scanData.getType().equals(NotifyType.EMAIL.getValue())) {
            template.setTitle(scanData.getTitle());
            template.setSendingType(SendingTypeEnum.EMAIL.getValue());
            template.setContent(scanData.getContent());
        }
        if (scanData.getType().equals(NotifyType.PM.getValue())) {
            template.setTitle(scanData.getTitle());
            template.setSendingType(SendingTypeEnum.PM.getValue());
            template.setContent(scanData.getContent());
        }
        if (scanData.getType().equals(NotifyType.SMS.getValue())) {
            template.setSendingType(NotifyType.SMS.getValue());
            template.setContent(scanData.getContent());
        }
        return template;
    }

    public static BusinessTypeDTO convertBusinessTypeDTO(final SendSettingDTO sendSetting) {
        return new BusinessTypeDTO(sendSetting.getId(), sendSetting.getCode(), sendSetting.getName());
    }

    public static String convertMapToJson(final ObjectMapper objectMapper, final Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new CommonException("error.convertMapToJson.JsonProcessing", e);
        }
    }

    public static Map<String, Object> convertJsonToMap(final ObjectMapper objectMapper, final String mapJson) {
        try {
            if (StringUtils.isEmpty(mapJson)) {
                return new HashMap<>(0);
            }
            JavaType jvt = objectMapper.getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);
            return objectMapper.readValue(mapJson, jvt);
        } catch (IOException e) {
            throw new CommonException("error.convertJsonToMap.IO", e);
        }
    }

}
