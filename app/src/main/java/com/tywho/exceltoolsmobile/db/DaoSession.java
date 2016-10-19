package com.tywho.exceltoolsmobile.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tywho.exceltoolsmobile.bean.QuestionContent;
import com.tywho.exceltoolsmobile.bean.QuestionType;

import com.tywho.exceltoolsmobile.db.QuestionContentDao;
import com.tywho.exceltoolsmobile.db.QuestionTypeDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig questionContentDaoConfig;
    private final DaoConfig questionTypeDaoConfig;

    private final QuestionContentDao questionContentDao;
    private final QuestionTypeDao questionTypeDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        questionContentDaoConfig = daoConfigMap.get(QuestionContentDao.class).clone();
        questionContentDaoConfig.initIdentityScope(type);

        questionTypeDaoConfig = daoConfigMap.get(QuestionTypeDao.class).clone();
        questionTypeDaoConfig.initIdentityScope(type);

        questionContentDao = new QuestionContentDao(questionContentDaoConfig, this);
        questionTypeDao = new QuestionTypeDao(questionTypeDaoConfig, this);

        registerDao(QuestionContent.class, questionContentDao);
        registerDao(QuestionType.class, questionTypeDao);
    }
    
    public void clear() {
        questionContentDaoConfig.clearIdentityScope();
        questionTypeDaoConfig.clearIdentityScope();
    }

    public QuestionContentDao getQuestionContentDao() {
        return questionContentDao;
    }

    public QuestionTypeDao getQuestionTypeDao() {
        return questionTypeDao;
    }

}