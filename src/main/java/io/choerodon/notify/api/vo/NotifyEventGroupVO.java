package io.choerodon.notify.api.vo;

/**
 * 〈功能简述〉
 * 〈通知设置分组VO〉
 *
 * @author wanghao
 * @Date 2019/12/11 11:14
 */
public class NotifyEventGroupVO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
