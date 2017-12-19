package com.example.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.greendao.bean.DaoMaster;
import com.example.greendao.bean.DaoSession;
import com.example.greendao.bean.StudentMsgBean;
import com.example.greendao.bean.StudentMsgBeanDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DaoSession daoSession = null;
    StudentMsgBeanDao msgBeanDao = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGreen();
        add("hh123");
//        add("hh111");
//        add("hh222");

        List<StudentMsgBean> list = query();
        for (int i = 0; i < list.size(); i++) {
            Log.i("======", "onCreate: " + list.get(i).getName());
        }
    }

    public void initGreen(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "student.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        msgBeanDao = daoSession.getStudentMsgBeanDao();
    }

    public void add(String name){

        StudentMsgBean studentMsgBean = new StudentMsgBean();
        studentMsgBean.setName(name);
        studentMsgBean.setStudentNum("123456");
        msgBeanDao.insert(studentMsgBean);
    }

    public void delete(){
        List<StudentMsgBean> list = msgBeanDao.queryBuilder()
                .build().list();
        for (int i = 0; i < list.size(); i++) {
            Log.d("zoneLog", "studentNumber: " + list.get(i).getStudentNum());
            Log.d("zoneLog", "name: " + list.get(i).getName());
            if (i == 0) {
                msgBeanDao.deleteByKey(list.get(0).getId());//通过 Id 来删除数据
                msgBeanDao.delete(list.get(0));//通过传入实体类的实例来删除数据
            }
        }
    }

    public void update(){
        List<StudentMsgBean> list = msgBeanDao.queryBuilder()
                .build().list();
        for (int i = 0; i < list.size(); i++) {
            Log.d("zoneLog", "studentNumber: " + list.get(i).getStudentNum());
            Log.d("zoneLog", "name: " + list.get(i).getName());
            if (i == 0) {
                list.get(0).setName("zone==========>");
                msgBeanDao.update(list.get(0));
            }
        }
    }

    public List<StudentMsgBean> query(){
        List<StudentMsgBean> list = msgBeanDao.queryBuilder()
                .offset(1)//偏移量，相当于 SQL 语句中的 skip
                .limit(3)//只获取结果集的前 3 个数据
                .orderAsc(StudentMsgBeanDao.Properties.StudentNum)//通过 StudentNum 这个属性进行正序排序
                .where(StudentMsgBeanDao.Properties.Name.eq("zone"))//数据筛选，只获取 Name = "zone" 的数据。
                .build()
                .list();

        return list;
    }
}
