package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;
import pers.jiangyinzuo.chat.domain.repository.FriendRepo;
import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_user")
public class User implements SessionCardCmpController.Session {
    private static String DEFAULT_AVATAR_URL;

    @FieldMapper(name = "user_id")
    private Long userId;

    @FieldMapper(name = "user_name")
    private String userName;

    @FieldMapper(name = "password")
    private String password;

    @FieldMapper(name = "intro")
    private String intro;

    @FieldMapper(name = "user_avatar")
    private String avatar;

    /**
     * 作为好友时的好友分组
     */
    @FieldMapper(name = "chat_friendship.friend_category")
    private String friendCategory;

    private Integer isOnline = 0;

    private GroupRepo groupRepo;
    private FriendRepo friendRepo;

    public User(Long userId, String userName, String password, String intro, String avatar, Integer unhandledNoticeCount) {
        this();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.intro = intro;
        this.avatar = avatar;
    }

    public User() {
        DEFAULT_AVATAR_URL = "file:" + URLDecoder.decode(User.class.getClassLoader().getResource("avatar.png").getPath(), StandardCharsets.UTF_8);
        this.groupRepo = new GroupRepo();
        this.friendRepo = new FriendRepo();
    }

    private User(Builder builder) {
        setUserId(builder.userId);
        setUserName(builder.userName);
        setPassword(builder.password);
        setIntro(builder.intro);
        setAvatar(builder.avatar);
        setFriendCategory(builder.friendCategory);
        isOnline = builder.isOnline;
        groupRepo = builder.groupRepo;
        friendRepo = builder.friendRepo;
    }

    public List<User> getFriendList() {
        return friendRepo.getFriendList(userId);
    }

    public List<Long> getFriendIdList() {
        return friendRepo.getFriendIdList(userId);
    }

    public List<Group> getGroupList(boolean update) {
        return groupRepo.getGroupListByUserId(userId, update);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFriendCategory() {
        return friendCategory;
    }

    public void setFriendCategory(String friendCategory) {
        this.friendCategory = friendCategory;
    }

    @Override
    public String getSessionName() {
        return getUserName();
    }

    @Override
    public String getAvatar() {
        return "".equals(avatar) || avatar == null ? DEFAULT_AVATAR_URL : avatar;
    }

    @Override
    public Long getId() {
        return getUserId();
    }

    @Override
    public String getStatus() {
        if (isOnline == 1) {
            return "在线";
        } else {
            return "离线";
        }
    }

    public void setAvatar(String avatar) {
        this.avatar = "".equals(avatar) || avatar == null ? DEFAULT_AVATAR_URL : avatar;
    }

    public Integer isOnline() {
        return isOnline;
    }

    public void setOnline(Integer online) {
        isOnline = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public static final class Builder {
        private Long userId;
        private String userName;
        private String password;
        private String intro;
        private String avatar = DEFAULT_AVATAR_URL;
        private String friendCategory;
        private Integer isOnline;
        private GroupRepo groupRepo;
        private FriendRepo friendRepo;

        public Builder() {
        }

        public Builder userId(Long val) {
            userId = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder intro(String val) {
            intro = val;
            return this;
        }

        public Builder avatar(String val) {
            avatar = val;
            return this;
        }

        public Builder friendCategory(String val) {
            friendCategory = val;
            return this;
        }

        public Builder isOnline(Integer val) {
            isOnline = val;
            return this;
        }

        public Builder groupRepo(GroupRepo val) {
            groupRepo = val;
            return this;
        }

        public Builder friendRepo(FriendRepo val) {
            friendRepo = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static void main(String[] args) {
        User user = new Builder().build();
        try {
            System.out.println(URLDecoder.decode(user.getAvatar(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
