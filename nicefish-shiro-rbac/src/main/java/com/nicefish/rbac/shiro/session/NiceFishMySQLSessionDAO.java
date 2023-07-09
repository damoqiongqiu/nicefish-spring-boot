package com.nicefish.rbac.shiro.session;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

/**
 * 此 DAO 实现以下功能：
 * - 把 Session 持久化到 MySQL 数据库。
 * - 缓存的 Session 和 MySQL 数据库中的 Session 同步。
 * 参考了 spring-session-jdbc 的实现，Session 中的所有 attributes 都会被存储到 SESSION_DATA 列中，但是不把整个 Session 对象序列化到数据库中，
 * 因为在跨项目共享 Session 时，如果 Session 中包含了某项目中特有的类，那么其它项目在反序列化时会失败。
 * @author 大漠穷秋
 */
public class NiceFishMySQLSessionDAO extends EnterpriseCacheSessionDAO {
    //TODO:实现 Session 持久化到 MySQL 数据库的逻辑。
    //TODO:实现缓存的 Session 和 MySQL 数据库中的 Session 同步的逻辑。
}
