package io.choerodon.notify.infra.asserts;

import io.choerodon.core.exception.FeignException;
import io.choerodon.notify.infra.dto.Template;
import io.choerodon.notify.infra.mapper.TemplateMapper;
import org.springframework.stereotype.Component;

/**
 * @author superlee
 * @since 2019-05-17
 */
@Component
public class TemplateAssertHelper {

    private TemplateMapper templateMapper;

    public TemplateAssertHelper(TemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }

    public Template templateNotExisted(Long id) {
        return templateNotExisted(id, "error.template.not.exist");
    }

    public Template templateNotExistedNotById(String code, String type) {
        return templateNotExistedNotById(code, type, "error.template.not.exist");
    }

    public Template templateNotExisted(Long id, String message) {
        Template template = templateMapper.selectByPrimaryKey(id);
        if (template == null) {
            throw new FeignException(message);
        }
        return template;
    }

    public Template templateNotExistedNotById(String code, String type, String message) {
        Template template = new Template();
        template.setSendSettingCode(code);
        template.setSendingType(type);
        template = templateMapper.selectOne(template);
        if (template == null) {
            throw new FeignException(message);
        }
        return template;
    }
}
