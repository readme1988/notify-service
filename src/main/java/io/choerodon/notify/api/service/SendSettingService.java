package io.choerodon.notify.api.service;

import com.github.pagehelper.PageInfo;
import io.choerodon.notify.api.dto.*;
import io.choerodon.notify.api.vo.WebHookVO;
import io.choerodon.notify.infra.dto.SendSettingDTO;
import io.choerodon.swagger.notify.NotifyBusinessTypeScanData;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface SendSettingService {
    SendSettingVO query(String code);

    void createByScan(Set<NotifyBusinessTypeScanData> businessTypes);

    List<SendSettingDetailTreeDTO> queryByLevelAndAllowConfig(String level, boolean allowConfig);

    void delete(Long id);

    /**
     * 消息服务页
     * 分页过滤接口
     *
     * @param messageType
     * @param introduce
     * @param enabled
     * @param allowConfig
     * @param params
     * @param pageable    分页信息
     * @return 分页结果
     */
    PageInfo<MessageServiceVO> pagingAll(String messageType, String introduce, Boolean enabled, Boolean allowConfig, String params, Pageable pageable, String firstCode, String secondCode);

    /**
     * 根据id启用消息服务（对应表；notify_send_setting）
     *
     * @return 启用的消息服务信息
     */
    MessageServiceVO enabled(String code);

    /**
     * 根据id停用消息服务（对应表；notify_send_setting）
     *
     * @return 停用的消息服务信息
     */
    MessageServiceVO disabled(String code);

    List<MsgServiceTreeVO> getMsgServiceTree();

    /**
     * 修改发送设置
     *
     * @param id
     * @param sendSettingDTO 更新信息
     */
    SendSettingDTO updateSendSetting(Long id, SendSettingDTO sendSettingDTO);

    /**
     * 查询项目层下的所有可选的SendSetting
     */
    WebHookVO.SendSetting getUnderProject();

    /**
     * 根据id
     * 允许配置接受设置
     * 对应表；notify_send_setting
     *
     * @param id 记录主键
     * @return 消息服务信息
     */
    MessageServiceVO allowConfiguration(Long id);

    /**
     * 根据id
     * 禁止配置接受设置
     * 对应表；notify_send_setting
     *
     * @param id 记录主键
     * @return 消息服务信息
     */
    MessageServiceVO forbiddenConfiguration(Long id);
    /**
     * 根据code查询发送设置
     * @param code
     * @return
     */
    SendSettingDTO queryByCode(String code);

    /**
     * 校验资源删除验证通知是否启用
     * @return
     */
    Boolean checkResourceDeleteEnabled();

}
