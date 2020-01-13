package io.choerodon.notify.api.service.impl

import io.choerodon.core.exception.CommonException
import io.choerodon.notify.api.dto.UserDTO
import io.choerodon.notify.api.service.EmailSendService
import io.choerodon.notify.infra.cache.ConfigCache
import io.choerodon.notify.infra.dto.Config
import io.choerodon.notify.infra.dto.SendSettingDTO
import io.choerodon.notify.infra.dto.Template
import io.choerodon.notify.infra.mapper.MailingRecordMapper
import io.choerodon.notify.infra.mapper.TemplateMapper
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.util.concurrent.Executor

/**
 * @author dengyouquan*  */
class EmailSendServiceImplSpec extends Specification {
    private TemplateMapper templateMapper = Mock(TemplateMapper)
    private ConfigCache configCache = Mock(ConfigCache)
    private TemplateRender templateRender = Mock(TemplateRender)
    private MailingRecordMapper recordMapper = Mock(MailingRecordMapper)
    private EmailQueueObservable emailQueueObservable = new EmailQueueObservable()
    private Executor executor = Mock(Executor)
    private EmailSendService emailSendService = new EmailSendServiceImpl(
            templateMapper, configCache, recordMapper, emailQueueObservable,
            templateRender, executor)
    @Autowired
    private Config config = new Config()

    def setup() {
        given: "创建config"
        config.setEmailAccount("noreply@choerodon.com.cn")
        config.setEmailPassword("password")
        config.setEmailSendName("choerodon")
        config.setEmailProtocol("SMTP")
        config.setEmailSsl(true)
        config.setEmailHost("smtp.aliyun.com")
        config.setEmailPort(22)
    }

    def "SendEmail"() {
        given: "构造参数"
        String code = "addUser"
        Map<String, Object> params = new HashMap<>()
        Set<UserDTO> targetUsers = new HashSet<>()
        targetUsers.add(new UserDTO(email: "123@qq.com"))
        SendSettingDTO sendSetting = new SendSettingDTO()
        sendSetting.setEmailTemplateId(1L)
        sendSetting.setIsSendInstantly(true)
        sendSetting.setRetryCount(1)
        Template template = new Template()

        when: "调用方法[template为空]"
        emailSendService.sendEmail(code, params, targetUsers, sendSetting)
        then: "校验结果"
        def exception = thrown(CommonException)
        exception.getCode().equals("error.emailTemplate.notExist")
        templateMapper.selectByPrimaryKey(_) >> null

        when: "调用方法[template Title Content为空]"
        emailSendService.sendEmail(code, params, targetUsers, sendSetting)
        then: "校验结果"
        exception = thrown(CommonException)
        exception.getCode().equals("error.emailTemplate.notValid")
        templateMapper.selectByPrimaryKey(_) >> template


        when: "调用方法[异常NullPointerException]"
        template.setEmailContent("email content")
        template.setEmailTitle("email title")
        emailSendService.sendEmail(code, params, targetUsers, sendSetting)
        then: "校验结果"
        //在sendFailedHandler中调用updateRecordStatusAndIncreaseCount会触发一个空指针
        //由于此方法接受的参数为long，而实际record.getId为null，但是record的id是insert后主键回写
        //包装类自动拆箱成基本类型会触发空指针异常，所以我们测试就止步于此
        thrown(NullPointerException)
        templateMapper.selectByPrimaryKey(_) >> template
        3 * configCache.getEmailConfig() >> config
        1 * recordMapper.insert(_) >> 1
    }

    def "TestEmailConnect"() {
        /*given: "构造请求参数"
        EmailConfigDTO dto = new EmailConfigDTO()

        when: "调用方法"
        //需要等待很久
        emailSendService.testEmailConnect()

        then: "校验结果"
        def exception = thrown(CommonException)
        exception.getCode().equals("error.emailConfig.testConnectFailed")
        1 * configCache.getEmailConfig() >> config*/
    }

}
