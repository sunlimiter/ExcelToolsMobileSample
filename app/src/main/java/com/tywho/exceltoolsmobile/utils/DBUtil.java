package com.tywho.exceltoolsmobile.utils;

import android.content.Context;
import android.database.Cursor;

import com.tywho.exceltoolsmobile.bean.QuestionContent;
import com.tywho.exceltoolsmobile.bean.QuestionType;
import com.tywho.exceltoolsmobile.db.DaoSession;
import com.tywho.exceltoolsmobile.db.QuestionContentDao;
import com.tywho.exceltoolsmobile.db.QuestionTypeDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 11:13
 */
public class DBUtil {
    private static DBUtil singleDB = null;
    private DaoSession daoSession = null;
    private QuestionContentDao questionContentDao = null;
    private QuestionTypeDao questionTypeDao = null;

    private DBUtil(Context context) {
        daoSession = DBUtilBase.getInstance().getDaoSession(context);
        questionContentDao = daoSession.getQuestionContentDao();
        questionTypeDao = daoSession.getQuestionTypeDao();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public static DBUtil getDbInstance(Context context) {
        if (singleDB == null) {
            singleDB = new DBUtil(context);
        }
        return singleDB;
    }

    public List<QuestionContent> selectLike(String string, int offset) {
        Query<QuestionContent> queryBuilder = questionContentDao.queryBuilder()
                .orderAsc(QuestionContentDao.Properties.Id)
                .limit(15)
                .where(QuestionContentDao.Properties.Content.like("%" + string + "%"))
                .where(new WhereCondition.StringCondition(" 1 GROUP BY " + QuestionContentDao.Properties.Locationx.name + "," + QuestionContentDao.Properties.Parentid.name))
                .offset(offset)
                .build();
        List<QuestionContent> infoArray = queryBuilder
                .list();
        return infoArray;
    }

    public List<QuestionContent> selectAllContent(long locationx, long parentid) {
        Query<QuestionContent> queryBuilder = questionContentDao.queryBuilder()
                .orderAsc(QuestionContentDao.Properties.Id)
                .where(QuestionContentDao.Properties.Locationx.eq(locationx))
                .where(QuestionContentDao.Properties.Parentid.eq(parentid))
                .build();
        List<QuestionContent> infoArray = queryBuilder
                .list();
        return infoArray;
    }

    public List<QuestionContent> selectAllTitle(long parentid) {
        Query<QuestionContent> queryBuilder = questionContentDao.queryBuilder()
                .orderAsc(QuestionContentDao.Properties.Id)
                .where(QuestionContentDao.Properties.Locationx.eq(0))
                .where(QuestionContentDao.Properties.Parentid.eq(parentid))
                .build();
        List<QuestionContent> infoArray = queryBuilder
                .list();
        return infoArray;
    }

    public QuestionType selectType(long parentid) {
        Query<QuestionType> queryBuilder = questionTypeDao.queryBuilder()
                .where(QuestionTypeDao.Properties.Id.eq(parentid))
                .build();
        QuestionType infoArray = queryBuilder
                .unique();
        return infoArray;
    }

    public List<Map<String, Object>> selectTotal() {
        String query = "SELECT COUNT(*) AS number,b.name from "
                + QuestionContentDao.TABLENAME
                + " as a LEFT JOIN " + QuestionTypeDao.TABLENAME + " as b ON a.parentid=b.id GROUP BY a.parentid";
        System.out.println(query);
        Integer count = 0;

        Cursor cursor = daoSession.getDatabase().rawQuery(
                query, null
        );
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            map = new HashMap<>();
            map.put("number", cursor.getInt(cursor.getColumnIndex("number")));
            map.put("name", cursor.getString(cursor.getColumnIndex("name")));
            list.add(map);
        }
        cursor.close();

        return list;
    }

    public long queryTotal() {
        return questionContentDao.queryBuilder().buildCount().count();
    }
}
