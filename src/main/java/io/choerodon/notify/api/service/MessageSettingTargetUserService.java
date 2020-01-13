package io.choerodon.notify.api.service;

import io.choerodon.notify.infra.dto.TargetUserDTO;

import java.util.List;

/**
 * 〈功能简述〉
 * 〈消息通知用户Service〉
 *
 * @author wanghao
 * @Date 2019/12/12 17:50
 */
public interface MessageSettingTargetUserService {

    void save(TargetUserDTO targetUserDTO);

    void deleteBySettingId(Long id);

    List<TargetUserDTO> getBySettingId(Long id);
}
