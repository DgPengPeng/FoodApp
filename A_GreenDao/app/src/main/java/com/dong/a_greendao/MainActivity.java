package com.dong.a_greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        // 各种初始化操作
        // helper类的初始化
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "MyFile.db", null);
        // 初始化master
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        // 初始化session
        DaoSession session = master.newSession();
        // xxxDao 初始化
        // 这个对象就是具体对数据库操作的对象
        personDao = session.getPersonDao();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                for (int i = 0; i < 30; i++) {
                    Person person = new Person(null, "四四", "男", 25 + i);
                    // 增的方法
                    personDao.insert(person);

                }
                break;
            case R.id.btn_delete:
                // 查找数据库中年龄为27  的person
                Person delePerson = personDao.queryBuilder().where(PersonDao.Properties.Age.eq(27)).build().unique();
                if (delePerson != null) {
                    // 通过找到这个person 的id 然后进行删除操作
                    personDao.deleteByKey(delePerson.getId());
                    // 删除全部
                    personDao.delete(delePerson);
                    // 删除数据库(全部)
                    personDao.deleteAll();
                }
                break;
            case R.id.btn_update:
                // 根据某一字段对数据的内容进行修改
                Person updatePerson = personDao.queryBuilder().where(PersonDao.Properties.Age.eq("35")).build().unique();
                if (updatePerson != null) {
                    updatePerson.setName("阿旺");
                    personDao.update(updatePerson);
                }
                break;
            case R.id.btn_query:
                // 查询所有数据
                List<Person> list = personDao.loadAll();
                for (Person person : list) {
                    Log.d("sss",
                            person.getName() + " "
                                    + person.getSex() + " "
                                    + person.getAge());
                }
                break;
        }
    }
}
