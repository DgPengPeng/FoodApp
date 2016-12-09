package com.dong.a_greendao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by dllo on 16/12/7.
 */
public class DBTool {
    private static DBTool ourInstance = new DBTool();
    private static PersonDao personDao;

    // 对外提供getInstance 方法 获取本类的单例对象
    public static DBTool getInstance() {
        if (ourInstance == null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }

        }
        // 初始化XXXDao 对象
        personDao = MyApp.getDaoSession().getPersonDao();
        return ourInstance;
    }

    private DBTool() {


    }

    // 增加单一对象的方法
    public void insertPerson(Person person) {
        personDao.insert(person);
    }

    // 增加集合的方法
    public void insertList(List<Person> list) {
        personDao.insertInTx(list);
    }

    //删除 单一对象的方法
    public void deletePerson(Person person) {
        personDao.delete(person);
    }

    // 删除集合的方法
    public void deleteAll() {
        personDao.deleteAll();
    }

    // 根据id进行删除
    public void deleteById(Long id) {
        personDao.deleteByKey(id);
    }

    // 根据某一字段进行删除操作
    public void deleteByName(String name) {
        DeleteQuery<Person> deleteQuery = personDao.queryBuilder()
                .where(PersonDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();

    }
    // 根据姓名 性别 年龄 进行删除
    public void deleteBy(String name, String sex,int age){
        DeleteQuery<Person> deleteQuery = personDao.queryBuilder().where(PersonDao.Properties.Name.eq(name),PersonDao.Properties.Sex.eq(sex),PersonDao.Properties.Age.eq(age)).buildDelete();
        if (deleteQuery == null) {
            deleteQuery.executeDeleteWithoutDetachingEntities();
        }
    }

    // 查询所有方法
    public List<Person> queryAll(){
        // 查询方法一
        List<Person> list = personDao.loadAll();
        // 查询方法二
        List<Person> personList = personDao.queryBuilder().list();
        return list;
    }

    // 查重方法
    // 根据姓名来查询
    public boolean isSave(String name){
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder().where(PersonDao.Properties.Name.eq(name));
        // 获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
    public boolean isSave(Person person){
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder().where(
                PersonDao.Properties.Name.eq(person.getName()),
                PersonDao.Properties.Age.eq(person.getAge()),
                PersonDao.Properties.Sex.eq(person.getSex()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
