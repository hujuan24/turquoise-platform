package ltd.newbee.mall.service;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface TurquoiseContentService {
    /**
     * 获取内容列表
     * @param pageUtil
     * @return
     */
    PageResult getContentPage(PageQueryUtil pageUtil);

    /**
     * 根据ID获取内容
     * @param contentId
     * @return
     */
    Object getContentById(Long contentId);

    /**
     * 保存内容
     * @param content
     * @return
     */
    boolean saveContent(Object content);

    /**
     * 更新内容
     * @param content
     * @return
     */
    boolean updateContent(Object content);

    /**
     * 删除内容
     * @param contentId
     * @return
     */
    boolean deleteContent(Long contentId);

    /**
     * 更新内容状态
     * @param contentId
     * @param status
     * @return
     */
    boolean updateContentStatus(Long contentId, int status);
}
