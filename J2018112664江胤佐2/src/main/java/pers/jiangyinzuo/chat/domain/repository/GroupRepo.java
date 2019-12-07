package pers.jiangyinzuo.chat.domain.repository;

import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class GroupRepo {

    /**
     * 用户的群聊列表
     */
    private List<Group> userGroupList = null;

    /**
     * 群成员ID与实体类的映射
     */
    private Map<Long, User> memberMap = null;

    /**
     * 获取用户群聊名单
     * @param userId 用户id
     * @param update 是否为更新操作
     * @return 群聊列表
     */
    public List<Group> getGroupListByUserId(Long userId, boolean update) {
        if (!update && userGroupList != null) {
            return userGroupList;
        }
        String sql = "SELECT chat_group.* FROM " +
                "chat_user_group_relation, chat_group " +
                "WHERE chat_user_group_relation.user_id = ? " +
                "AND chat_user_group_relation.group_id = chat_group.group_id";
        List<Group> resultList = null;
        try {
            resultList = MySqlHelper.queryMany(Group.class, sql, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public Map<Long, User> getMemberMap(Long groupId, boolean update) {
        if (update || memberMap == null) {
            updateMemberMap(groupId);
        }
        return memberMap;
    }

    public void updateMemberMap(Long groupId) {
        String sql = "SELECT chat_user.* FROM chat_user, chat_user_group_relation WHERE group_id = ? AND chat_user.user_id = chat_user_group_relation.user_id";
        Map<Long, User> resultMap = new HashMap<>(20);
        try {
            List<User> list = MySqlHelper.queryMany(User.class, sql, groupId);
            for (User user : list) {
                resultMap.put(user.getUserId(), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        memberMap = resultMap;
    }
}
