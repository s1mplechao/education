package com.mz.stu.serivce.activi;


import com.mz.stu.entity.UserGroup;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Resource
    private IdentityService identityService;

    public boolean login(String userName, String password) {
        return identityService.checkPassword(userName, password);
    }

    public Object getAllUser() {
        List<User> userList = identityService.createUserQuery().list();
        return toMyUser(userList);
    }

    public Object getAllGroup() {
        List<Group> groupList = identityService.createGroupQuery().list();
        List<UserGroup> userGroupList = new ArrayList<>();
        for (Group group : groupList) {
            UserGroup userGroup = new UserGroup();
            userGroup.setId(group.getId());
            userGroup.setName(group.getName());
            userGroupList.add(userGroup);
        }
        return userGroupList;
    }

    public Object getUserGroup(String groupId) {
        List<User> userList = identityService.createUserQuery().memberOfGroup(groupId).list();
        return toMyUser(userList);
    }

    private List<com.mz.stu.entity.User> toMyUser(List<User> userList) {
        List<com.mz.stu.entity.User> myUserList = new ArrayList<>();
        for (User user : userList) {
            com.mz.stu.entity.User myUser = new com.mz.stu.entity.User();
            myUser.setUsername(user.getId());
            myUser.setPassword(user.getPassword());
            myUserList.add(myUser);
        }
        return myUserList;
    }

    public Object addUser(com.mz.stu.entity.User user) {
        String userId = user.getUsername();
        String groupId = user.getGroupId();
        User actUser = identityService.newUser(userId);
        actUser.setPassword(user.getPassword());
        identityService.saveUser(actUser);
        identityService.createMembership(userId, groupId);
        return true;
    }
}
