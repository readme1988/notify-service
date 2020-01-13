package io.choerodon.notify.api.controller.v1

import io.choerodon.core.domain.Page
import io.choerodon.core.exception.ExceptionResponse
import io.choerodon.notify.IntegrationTestConfiguration
import io.choerodon.notify.domain.Record
import io.choerodon.notify.infra.mapper.MailingRecordMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

/**
 * @author dengyouquan
 * */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class MessageRecordSiteControllerSpec extends Specification {
    private static final String BASE_PATH = "/v1/records"
    @Autowired
    private TestRestTemplate restTemplate
    @Autowired
    private MailingRecordMapper recordMapper
    @Shared
    List<Record> records = new ArrayList<>()
    @Shared
    def needInit = true
    @Shared
    def needClean = false
    @Shared
    def count = 3

    def setup() {
        if (needInit) {
            given: ""
            needInit = false
            for (int i = 0; i < count; i++) {
                Record record = new Record()
                record.setMessageType("email")
                record.setBusinessType("forgetPassword")
                record.setRetryCount(1)
                record.setFailedReason("fail")
                record.setSendData(null)
                records.add(record)
            }

            when: ""
            int num = 0
            for (Record record: records) {
                num++
                recordMapper.insert(record)
            }

            then: ""
            num == count
        }
    }

    def cleanup() {
        if (needClean) {
            given: ""
            needClean = false

            when: ""
            def num = 0
            for (Record record : records)
                num += recordMapper.delete(record)

            then: ""
            num == count
        }
    }

    def "PageEmail"() {
        given: "构造请求参数"
        def params = new HashMap<String, Object>()
        params.put("failedReason", "fail")

        when: "调用方法"
        def entity = restTemplate.getForEntity(BASE_PATH + "/emails?failedReason={failedReason}", Page, params)

        then: "校验结果"
        entity.getStatusCode().is2xxSuccessful()
        //没有template
        entity.getBody().size() == 0
    }

    def "ManualRetrySendEmail"() {
        given: "构造请求参数"
        def params = new HashMap<String, Object>()
        params.put("id", records.get(0).getId())
        needClean = true

        when: "调用方法"
        //组织层是get，全局曾是post
        def entity = restTemplate.postForEntity(BASE_PATH + "/emails/{id}/retry", void,ExceptionResponse, params)

        then: "校验结果"
        entity.getStatusCode().is2xxSuccessful()
        entity.getBody().getCode().equals("error.record.retryNotFailed")
    }
}
