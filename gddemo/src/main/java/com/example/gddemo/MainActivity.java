package com.example.gddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gddemo.database.greenDao.db.DaoSession;
import com.example.gddemo.database.greenDao.db.StudentDao;
import com.example.gddemo.entity.Student;
import com.example.gddemo.entity.StudentAndTeacherBean;
import com.example.gddemo.entity.Teacher;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Random mRandom;
    List<Teacher> teacherList = new ArrayList<>();
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRandom = new Random();
        teacherList.add(new Teacher(1, 1, "tel1", "", "", "", ""));
        teacherList.add(new Teacher(2, 2, "tel2", "", "", "", ""));
        teacherList.add(new Teacher(3, 3, "tel3", "", "", "", ""));
        teacherList.add(new Teacher(4, 4, "tel4", "", "", "", ""));
        teacherList.add(new Teacher(5, 5, "tel5", "", "", "", ""));
        teacherList.add(new Teacher(6, 6, "tel6", "", "", "", ""));
        teacherList.add(new Teacher(7, 7, "tel7", "", "", "", ""));
    }

    private void insertAllData() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setStudentNo(i);
            int age = mRandom.nextInt(10) + 10;
            student.setAge(age);
            student.setTelPhone("tel" + i);
            String chineseName = "name" + i;
            student.setName(chineseName);
            if (i % 2 == 0) {
                student.setSex("男");
            } else {
                student.setSex("女");
            }
            student.setAddress("address" + i);
            student.setGrade(String.valueOf(age % 10) + "年纪");
            student.setSchoolName("schoolName" + i);
            daoSession.insertOrReplace(student);//插入或替换
        }
    }


    /**
     * 删除所有
     */
    public void deleteAll() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        daoSession.deleteAll(Student.class);
    }

    /**
     * 删除单个
     */
    public void deleteData(Student s) {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        daoSession.delete(s);
    }

    /**
     * 更新单个
     */
    public void updateData(Student s) {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        daoSession.update(s);
    }

    /**
     * 查询所有
     */
    public List queryAll() {
        List<Student> students = ((MyApplication) getApplication()).getDaoSession().loadAll(Student.class);
        return students;
    }

    /**
     * 通过某个条件查询
     */
    public void queryData(String id) {
        List<Student> students = ((MyApplication) getApplication()).getDaoSession().queryRaw(Student.class, " where id = ?", id);
    }


    /**
     * 通过某个条件查询
     */
    public void queryDataSection() {
        List<Student> list = ((MyApplication) getApplication())
                .getDaoSession()
                .queryBuilder(Student.class)
                .where(StudentDao.Properties.StudentNo.ge(990))
                .list();
        for (Student student : list) {
            System.out.println(student);
        }
    }


    /**
     * 通过某个条件查询
     */
    public void deleteDataSectionLimit() {
        List<Student> list = ((MyApplication) getApplication())
                .getDaoSession()
                .queryBuilder(Student.class)
                .where(StudentDao.Properties.StudentNo.ge(0))
                .limit(10)
                .list();

        for (Student student : list) {
            System.out.println(student);
        }
//                .buildDelete()
//                .executeDeleteWithoutDetachingEntities();

    }

    /**
     * 通过某个条件查询
     */
    public void queryDataSectionLimit() {
        ((MyApplication) getApplication())
                .getDaoSession()
                .queryBuilder(Student.class)
                .where(StudentDao.Properties.StudentNo.ge(0))
                .limit(10)
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();

    }

    /**
     * where(WhereCondition cond, WhereCondition… condMore): 查询条件，参数为查询的条件！
     * <p>
     * or(WhereCondition cond1, WhereCondition cond2, WhereCondition… condMore): 嵌套条件或者，用法同 or。
     * <p>
     * and(WhereCondition cond1, WhereCondition cond2, WhereCondition… condMore): 嵌套条件且，用法同 and。
     * <p>
     * join(Property sourceProperty, Class destinationEntityClass):多表查询，后面会讲。
     * <p>
     * 输出结果有四种方式，选择其中一种最适合的即可，list()返回值是 List,而其他三种返回值均实现 Closeable,需要注意的不使用数据时游标的关闭操作：
     * <p>
     * list （）所有实体都加载到内存中。结果通常是一个没有魔法的 ArrayList。最容易使用。
     * <p>
     * listLazy （）实体按需加载到内存中。首次访问列表中的元素后，将加载并缓存该元素以供将来使用。必须关闭。
     * <p>
     * listLazyUncached （）实体的“虚拟”列表：对列表元素的任何访问都会导致从数据库加载其数据。必须关闭。
     * <p>
     * listIterator （）让我们通过按需加载数据（懒惰）来迭代结果。数据未缓存。必须关闭。
     * <p>
     * GreenDao 中 SQL 语句的缩写，我们也了解下，源码在Property中,使用的时候可以自己点进去查询即可：
     * <p>
     * eq()：“equal (’=?’)” 等于；
     * <p>
     * notEq() ：“not equal (’<>?’)” 不等于；
     * <p>
     * like()：" LIKE ?" 值等于；
     * <p>
     * between()：" BETWEEN ? AND ?" 取中间范围；
     * <p>
     * in()：" IN (" in命令;
     * <p>
     * notIn()：" NOT IN (" not in 命令;
     * <p>
     * gt()：">?" 大于;
     * <p>
     * lt()："<? " 小于;
     * <p>
     * ge()：">=?" 大于等于;
     * <p>
     * le()："<=? " 小于等于;
     * <p>
     * isNull()：" IS NULL" 为空;
     * <p>
     * isNotNull()：" IS NOT NULL" 不为空;
     */

    @butterknife.OnClick({R.id.btn_add_all, R.id.bt_delete_all, R.id.btn_query_section, R.id.btn_delete_section, R.id.btn_add_multi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_all:
                insertAllData();
                break;
            case R.id.bt_delete_all:
                deleteAll();
                break;
            case R.id.btn_query_section:
                queryDataSection();
                break;
            case R.id.btn_delete_section:
                deleteDataSectionLimit();
                break;

            case R.id.btn_add_multi:
                id = 1008615;
                addData(id++);
                break;
        }
    }


    public void addData(int id) {
        Student student = new Student();
        student.setStudentNo(id);
        int age = mRandom.nextInt(10) + 10;
        student.setAge(age);
        student.setTelPhone("tel" + id);
        student.setName("name" + id);
        if (id % 2 == 0) {
            student.setSex("男");
        } else {
            student.setSex("女");
        }
        student.setAddress("road");
        student.setGrade(String.valueOf(age % 10) + "年纪");
        student.setSchoolName("schoolName" + id);
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        daoSession.insertOrReplace(student);


        Collections.shuffle(teacherList);
        for (int j = 0; j < mRandom.nextInt(8) + 1; j++) {
            if (j < teacherList.size()) {
                Teacher teacher = teacherList.get(j);
                daoSession.insert(teacher);
                StudentAndTeacherBean teacherBean = new StudentAndTeacherBean(student.getId(), teacher.getId());
                daoSession.insert(teacherBean);
            }
        }
    }
}
