package com.nicefish.rbac.shiro.session;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

/**
 * 此 DAO 实现以下功能：
 * - 把 Session 持久化到 MySQL 数据库。
 * - 缓存的 Session 和 MySQL 数据库中的 Session 同步。
 * @author 大漠穷秋
 */
public class NiceFishMySQLSessionDAO extends EnterpriseCacheSessionDAO {
    //TODO:实现 Session 持久化到 MySQL 数据库的逻辑。
    //TODO:实现缓存的 Session 和 MySQL 数据库中的 Session 同步的逻辑。
}
