package io.choerodon.notify.infra.dto;

import io.choerodon.mybatis.entity.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jiameng.cao
 * @date 2019/10/25
 */
@Table(name = "notify_send_setting_category")
public class SendSettingCategoryDTO extends BaseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String description;

    private SendSettingDTO sendSettingDTO;

    public SendSettingDTO getSendSettingDTO() {
        return sendSettingDTO;
    }

    public void setSendSettingDTO(SendSettingDTO sendSettingDTO) {
        this.sendSettingDTO = sendSettingDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
