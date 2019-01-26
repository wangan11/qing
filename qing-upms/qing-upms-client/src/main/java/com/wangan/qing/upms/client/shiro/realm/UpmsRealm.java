package com.wangan.qing.upms.client.shiro.realm;

import com.wangan.qing.upms.dao.model.UpmsPermission;
import com.wangan.qing.upms.dao.model.UpmsRole;
import com.wangan.qing.upms.dao.model.UpmsUser;
import com.wangan.qing.upms.rpc.api.UpmsApiService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UpmsRealm  extends AuthorizingRealm {

    @Autowired
    private UpmsApiService apiService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UpmsUser upmsUser = apiService.findUserByUsername(username);
        //获取用户角色
        List<UpmsRole> upmsRoles = apiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        Set<String> roles=new HashSet<>();
        for (UpmsRole upmsRole:upmsRoles){
            if(StringUtils.isNotEmpty(upmsRole.getName())){
                roles.add(upmsRole.getName());
            }
        }
        //获取用户权限
        List<UpmsPermission> upmsPermissions = apiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions=new HashSet<>();
        for(UpmsPermission upmsPermission:upmsPermissions){
            if(StringUtils.isNotEmpty(upmsPermission.getPermissionValue())){
                upmsPermissions.add(upmsPermission);
            }
        }
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
