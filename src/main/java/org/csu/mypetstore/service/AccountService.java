package org.csu.mypetstore.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    /**
     * 用户模块
     */

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 注册验证：给定username查看用户
     * @param username
     * @return
     */
    public Account getAccount(String username){return accountMapper.getAccountByUsername(username);}

    /**
     * 登陆验证：username和password，在数据库中看能否查到
     * @param username
     * @param password
     * @return
     */
    public Account getAccount(String username,String password){
        String hashPassword = new SimpleHash("SHA-256", password, username+"reg", 1024).toString();
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(hashPassword);
//        System.out.println("hashPassword is " + hashPassword);
        return accountMapper.getAccountByUsernameAndPassword(account);
    }

    /**
     * 注册账号
     * @param account
     */
    @Transactional
    public void insertAccount(Account account){
        String password = account.getPassword();
        String username = account.getUsername();
        String hashPassword = new SimpleHash("SHA-256", password, username+"reg", 1024).toString();
        account.setPassword(hashPassword);
        accountMapper.insertAccount(account);
        accountMapper.insertProfile(account);
        accountMapper.insertSignon(account);
    }

    /**
     * 更改信息
     * @param account
     */
    @Transactional
    public void update_info(Account account){
        accountMapper.updateAccount(account);
        accountMapper.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountMapper.updateSignon(account);
        }
    }

}
